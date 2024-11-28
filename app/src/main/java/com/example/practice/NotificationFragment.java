package com.example.practice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
ArrayList<NotificationModel> arrayNotification= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerNotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));

        arrayNotification.add(new NotificationModel(R.drawable.boy,"Notification Title","Notification Description","2023-11-01","10:00 AM"));
        arrayNotification.add(new NotificationModel(R.drawable.girl,"Notification Title","Notification Description","2024-12-27","7:00 PM"));
        arrayNotification.add(new NotificationModel(R.drawable.boy,"Notification Title","Notification Description","2023-11-01","10:00 AM"));
        arrayNotification.add(new NotificationModel(R.drawable.girl,"Notification Title","Notification Description","2024-12-27","7:00 PM"));
        arrayNotification.add(new NotificationModel(R.drawable.boy,"Notification Title","Notification Description","2023-11-01","10:00 AM"));
        arrayNotification.add(new NotificationModel(R.drawable.girl,"Notification Title","Notification Description","2024-12-27","7:00 PM"));

        RecyclerNotificationAdapter adapter = new RecyclerNotificationAdapter(view.getContext(),arrayNotification);
        recyclerView.setAdapter(adapter);
        return view;
    }
}