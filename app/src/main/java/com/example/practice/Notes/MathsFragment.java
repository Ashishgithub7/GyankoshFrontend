package com.example.practice.Notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.practice.Entity.NoteTopic;
import com.example.practice.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MathsFragment extends Fragment {
    private ExpandableListView expandableListView;
    private List<NoteTopic> topics;


    public MathsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maths, container, false);
        expandableListView = view.findViewById(R.id.expandableListView);
        prepareData();
        TopicExpandableListAdapter adapter = new TopicExpandableListAdapter(requireContext(), topics);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String subtopic = topics.get(groupPosition).getSubtopics().get(childPosition);

            // Navigate to the NotesActivity with the selected subtopic
            Intent intent = new Intent(getContext(), Note.class);
            intent.putExtra("subtopic", subtopic);
            startActivity(intent);

            return true;
        });
        return view;

    }
    private void prepareData() {
        // Hardcoded topics and subtopics for simplicity
        topics = new ArrayList<>();
        topics.add(new NoteTopic("Set, Logic and Functions", Arrays.asList("Set Theory","Relation and Function")));
        topics.add(new NoteTopic("Algebra", Arrays.asList("Matrix and Determinant","Complex Number")));
        topics.add(new NoteTopic("Trigonometry", Arrays.asList("Trigonometric Equations and General Values")));
        topics.add(new NoteTopic("Calculus", Arrays.asList("Limit and Continuity")));
        topics.add(new NoteTopic("Coordinate Geometry", Arrays.asList("Straight Line","Circle")));
        topics.add(new NoteTopic("Statistics and Probability", Arrays.asList("Statistics","Probability")));
        // Add more topics and subtopics as needed
    }
}