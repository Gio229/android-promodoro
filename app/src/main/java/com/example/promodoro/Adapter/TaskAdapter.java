package com.example.promodoro.Adapter;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.example.promodoro.MainActivity;
        import com.example.promodoro.Model.JsonDataMaker;
        import com.example.promodoro.Model.TafModel;
        import com.example.promodoro.Model.TaskModel;
        import com.example.promodoro.R;
        import com.example.promodoro.TafDetailsActivity;

        import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModel> taskList;
    private final TafDetailsActivity activity;
    private TafModel myActivity;

    public TaskAdapter(TafDetailsActivity activity){
        this.activity = activity;

        JsonDataMaker dataManager = new JsonDataMaker(activity);
        myActivity = dataManager.getJsonOneActivity(Integer.parseInt(activity.getIntent().getExtras().getString("id")));
        taskList = myActivity.getTasks();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){

        if(taskList.size() != 0){
            TaskModel item = taskList.get(position);
            holder.myTaskName.setText(item.getTaskName());
            /*holder.myTaskTime.setText(item.getTaskTime());
            holder.myTaskTimeSpent.setText(item.getTaskTimeSpent());
            holder.myTaskTimeLeft.setText(item.getTaskTimeLeft());*/
            holder.myTaskCheckBox.setChecked(toBolean(item.getStatus()));
        }

    }

    public int getItemCount(){
        return taskList.size();
    }

    private boolean toBolean(int n){
        return n != 0;
    }

    public void setTask(List<TaskModel> taskList){
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTaskName;
        TextView myTaskTime;
        TextView myTaskTimeSpent;
        TextView myTaskTimeLeft;
        CheckBox myTaskCheckBox;
        ViewHolder(View view){
            super(view);
            myTaskName = view.findViewById(R.id.taskName);
            myTaskTime = view.findViewById(R.id.myTaskTime);
            myTaskTimeSpent = view.findViewById(R.id.myTaskTimeSpent);
            myTaskTimeLeft = view.findViewById(R.id.myTaskTimeLeft);
            myTaskCheckBox = view.findViewById(R.id.myTaskCheckBox);
        }
    }



}

