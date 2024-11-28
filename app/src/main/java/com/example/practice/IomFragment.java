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


public class IomFragment extends Fragment {
    private LinearLayout mcq,note,modelTest;
    public IomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_iom, container, false);

        mcq= view.findViewById(R.id.mcq);
        mcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), McqFirstPage.class);
                intent.putExtra("COURSE_NAME", "IOM");
                startActivity(intent);
            }
        });

        modelTest = view.findViewById(R.id.modelTest);
        modelTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ModelSetsActivity.class);
                intent.putExtra("COURSE_NAME", "IOM");
                startActivity(intent);
            }
        });

        note = view.findViewById(R.id.notes);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), NotesMain.class);
                intent.putExtra("COURSE_NAME", "IOM");
                startActivity(intent);
            }
        });

        return view;
    }
}