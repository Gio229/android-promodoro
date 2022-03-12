package com.example.promodoro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.promodoro.Adapter.TafAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback{

    private TafAdapter tafAdapter;

    public RecyclerItemTouchHelper(TafAdapter adapter){

        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.tafAdapter = adapter;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
        return false;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction){
        final int position = viewHolder.getAbsoluteAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            AlertDialog.Builder builder = new AlertDialog.Builder(tafAdapter.getContext());
            builder.setTitle("Suppression");
            builder.setMessage("Vous êtes sûre de vouloir supprimer cette activité ?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tafAdapter.deleteItem(position);
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tafAdapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            tafAdapter.editItem(position);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        Drawable icon;
        ColorDrawable background;

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        if(dX > 0){
            icon = ContextCompat.getDrawable(tafAdapter.getContext(), R.drawable.ic_baseline_edit);
            background = new ColorDrawable(ContextCompat.getColor(tafAdapter.getContext(), R.color.my_blue));
        }else{
            icon = ContextCompat.getDrawable(tafAdapter.getContext(), R.drawable.ic_baseline_delete);
            background = new ColorDrawable(Color.RED);
        }

        int iconMargin = ( itemView.getHeight() - icon.getIntrinsicHeight() ) / 2;
        int iconTop = itemView.getTop() + iconMargin;
        int iconBottom = iconTop + icon.getIntrinsicHeight();


        if(dX > 0){//swiper à droite
            int iconLeft = itemView.getLeft() + iconMargin;
            int iconRight = iconLeft + icon.getIntrinsicWidth();
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int)dX) + backgroundCornerOffset, itemView.getBottom());
        }else if(dX <0){//swiper à gauche
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int)dX) - backgroundCornerOffset, itemView.getTop(),
                    itemView.getRight(), itemView.getBottom());
        }else{
            background.setBounds(0,0,0,0);
        }
        background.draw(c);
        icon.draw(c);

    }

}
