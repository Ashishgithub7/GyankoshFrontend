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


public class ChemistryFragment extends Fragment {


    private ExpandableListView expandableListView;
    private List<NoteTopic> topics;

    public ChemistryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chemistry, container, false);

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
        topics.add(new NoteTopic("Physical Chemistry", Arrays.asList("Language of Chemistry","Laws of Stoichiometry","Oxidation and Reduction","Volumetric Analysis")));
        topics.add(new NoteTopic("Inorganic Chemistry", Arrays.asList("Hydrogen and Water","Oxygen and Ozone","Halogens")));
        topics.add(new NoteTopic("Organic Chemistry", Arrays.asList("Hydrocarbons Alkane","Hydrocarbons Alkenes","Hydrocarbons Alkynes","Alcohol","Ethers")));
        // Add more topics and subtopics as needed
    }
}