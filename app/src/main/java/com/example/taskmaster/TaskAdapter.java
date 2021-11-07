package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    List<Task> allTasksData = new ArrayList<>();
    private OnTaskItemClickListener onTaskItemClickListener;


    public TaskAdapter(List<Task> allTasksData, OnTaskItemClickListener onTaskItemClickListener) {
        this.allTasksData = allTasksData;
        this.onTaskItemClickListener = onTaskItemClickListener;
    }
    public interface OnTaskItemClickListener{
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        return new TaskViewHolder(view,onTaskItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.task = allTasksData.get(position);

        TextView title = holder.itemView.findViewById(R.id.titleFragment);
        TextView body = holder.itemView.findViewById(R.id.bodyFragment);
        TextView state = holder.itemView.findViewById(R.id.stateFragment);

        title.setText(holder.task.title);
        body.setText(holder.task.body);
        state.setText(holder.task.state);
    }

    @Override
    public int getItemCount() {
        return allTasksData.size();
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView, OnTaskItemClickListener listener) {
            super(itemView);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }

}
