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


public class EnglishFragment extends Fragment {
    private ExpandableListView expandableListView;
    private List<NoteTopic> topics;

    public EnglishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english, container, false);
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
        topics.add(new NoteTopic("Mechanics", Arrays.asList("Physical Quantities, Vector and Kinematics", "Newton Law of Motion and Friction","Work, Energy and Power","Circular Motion, Gravitation and SHM","Rotational Dynamics","Elasticity","Fluid Mechanics")));
        topics.add(new NoteTopic("Heat and Thermodynamics", Arrays.asList("Temperature and Quantity of Heat","Transfer of Heat","Thermal Properties of Matter","Laws of Thermodynamics")));
        topics.add(new NoteTopic("Geometric and Physical Optics", Arrays.asList("Reflection","Refraction","Dispersion","Nature and Propagation of Light","Interference","Diffraction","Polarization")));
        // Add more topics and subtopics as needed
    }
}