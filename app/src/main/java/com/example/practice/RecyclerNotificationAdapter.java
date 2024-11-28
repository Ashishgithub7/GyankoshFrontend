package com.example.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerNotificationAdapter extends RecyclerView.Adapter<RecyclerNotificationAdapter.ViewHolder> {
    Context context;
    ArrayList<NotificationModel> arrayNotification;

   RecyclerNotificationAdapter(Context context, ArrayList<NotificationModel> arrayNotification){
       this.context=context;
       this.arrayNotification=arrayNotification;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerNotificationAdapter.ViewHolder holder, int position) {
      holder.title.setText(arrayNotification.get(position).getTitle());
      holder.description.setText(arrayNotification.get(position).getDescription());
      holder.date.setText(arrayNotification.get(position).getDate());
      holder.time.setText(arrayNotification.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return arrayNotification.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView title,description,date,time;


        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notificationTitle);
            description = itemView.findViewById(R.id.notificationDescription);
            date = itemView.findViewById(R.id.notificationDate);
            time = itemView.findViewById(R.id.notificationTime);
        }
    }
}
