package com.example.practice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.practice.MCQ.McqFirstPage;
import com.example.practice.Model_Test.ModelSetsActivity;
import com.example.practice.Notes.NotesMain;

import Video.VideoMainActivity;


public class IoeFragment extends Fragment {
 private LinearLayout mcq,note,modelTest,video;

    public IoeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ioe, container, false);

        mcq= view.findViewById(R.id.mcq);
        mcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), McqFirstPage.class);
                intent.putExtra("COURSE_NAME", "IOE");
                startActivity(intent);
            }
        });

        note = view.findViewById(R.id.notes);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), NotesMain.class);
                intent.putExtra("feature","note");
                intent.putExtra("COURSE_NAME", "IOE");
                startActivity(intent);
            }
        });

        modelTest = view.findViewById(R.id.modelTest);
        modelTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ModelSetsActivity.class);
                intent.putExtra("COURSE_NAME", "IOE");
                startActivity(intent);
            }
        });

        video = view.findViewById(R.id.videos);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), NotesMain.class);
                intent.putExtra("feature","video");
                intent.putExtra("COURSE_NAME", "IOE");
                startActivity(intent);
            }
        });
        return view;
    }
}