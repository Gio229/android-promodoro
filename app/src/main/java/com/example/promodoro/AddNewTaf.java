package com.example.promodoro;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.promodoro.Model.JsonDataMaker;
import com.example.promodoro.Model.TafModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class AddNewTaf extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
    private EditText newTafText;
    private Button newTafSaveButton;
    private List<TafModel> db;
    private JsonDataMaker dataManager;

    private final MainActivity activity;

    public AddNewTaf(MainActivity activity){

        this.activity = activity;


    }


    public static AddNewTaf newInstance(MainActivity activity){
        return new AddNewTaf(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        View view = inflater.inflate(R.layout.new_taf, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        newTafText = getView().findViewById(R.id.newTafText);
        newTafSaveButton = getView().findViewById(R.id.newTafButton);

        dataManager = new JsonDataMaker(activity);
        db = dataManager.getJsonData();


        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if(bundle != null) {
            isUpdate = true;
            String taf = bundle.getString("tafName");//tafName
            newTafText.setText(taf);
            if (taf.length() > 0) {
                newTafSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.my_gray));
            }
        }

            newTafText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().equals("")){
                        newTafSaveButton.setEnabled(false);
                        newTafSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.my_gray));
                    }else {
                        newTafSaveButton.setEnabled(true);
                        newTafSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.my_blue));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            boolean finalIsUpdate = isUpdate;
            newTafSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = newTafText.getText().toString();
                    if(finalIsUpdate){
                        //on met à jour
                        int position = bundle.getInt("id");
                        db.get(position).setTafName(text);
                        Gson gson = new Gson();

                        dataManager.writeJson(gson.toJson(db));
                    }else{

                        if(!newTafText.getText().toString().equals("")){
                            //dans le cas contraire on ajoute
                            TafModel taf = new TafModel();
                            taf.setTafName(text);
                            taf.setStatus(0);
                            db.add(taf);

                            Gson gson = new Gson();



                            dataManager.writeJson(gson.toJson(db));
                        }

                    }
                    dismiss();


                }
            });



    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof  DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //onDestroy();
        dismiss();
    }

}
