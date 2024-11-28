package com.example.practice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.practice.MCQ.McqFirstPage;
import com.example.practice.Notes.NotesMain;


public class LoksewaFragment extends Fragment {
 private TextView courseName;
 private LinearLayout mcq,note;

    public LoksewaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_loksewa, container, false);
       mcq= view.findViewById(R.id.mcq);
       mcq.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(requireContext(), McqFirstPage.class);
               intent.putExtra("COURSE_NAME", "LOKSEWA");
               startActivity(intent);
           }
       });

        note = view.findViewById(R.id.notes);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), NotesMain.class);
                intent.putExtra("COURSE_NAME", "LOKSEWA");
                startActivity(intent);
            }
        });
       return view;
}
}
