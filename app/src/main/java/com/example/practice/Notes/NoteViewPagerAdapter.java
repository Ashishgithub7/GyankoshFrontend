package com.example.practice.Notes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import Video.PhysicsVideoFragment;

public class NoteViewPagerAdapter extends FragmentStateAdapter {
    private String course;
    private List<String> subjects;
    private String feature;

    public NoteViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> subjects,String feature) {
        super(fragmentActivity);
        this.subjects = subjects;
        this.feature=feature;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String subject = subjects.get(position);
        // Return the fragment based on the subject name
    if (feature.equals("note")){
        switch (subject) {
            case "Physics":
                return new PhysicsFragment();
            case "Chemistry":
                return new ChemistryFragment();
            case "Maths":
                return new MathsFragment();
            case "Science":
                return new ScienceFragment(); // Add this if you have a ScienceFragment
            case "English":
                return new EnglishFragment();
            case "Biology":
                return new BiologyFragment();
            case "G.K":
                return new GKFragment();
            case "S.English":
                return new S_EnglishFragment();
            case "S.Maths":
                return new S_MathsFragment();
            case "General Knowledge":
                return new General_KnowledgeFragment();
            // Add cases for other subjects as needed
            default:
                return new Intelligence_QuestionFragment(); // Handle unknown subjects
        }
    }
    else {
        switch (subject) {
            case "Physics":
                return new PhysicsVideoFragment();
            case "Chemistry":
                return new ChemistryFragment();
            case "Maths":
                return new MathsFragment();
            case "Science":
                return new ScienceFragment(); // Add this if you have a ScienceFragment
            case "English":
                return new EnglishFragment();
            case "Biology":
                return new BiologyFragment();
            case "G.K":
                return new GKFragment();
            case "S.English":
                return new S_EnglishFragment();
            case "S.Maths":
                return new S_MathsFragment();
            case "General Knowledge":
                return new General_KnowledgeFragment();
            // Add cases for other subjects as needed
            default:
                return new Intelligence_QuestionFragment(); // Handle unknown subjects
        }

    }

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }


}
