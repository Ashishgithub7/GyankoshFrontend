package com.example.practice;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
ArrayList<SearchModel> arraySearch= new ArrayList<>();
private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_search, container, false);
        searchView= view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));

        arraySearch.add(new SearchModel("Physical Quantities, Vector and Kinematics","Physics-Mechanics","IOM, IOE","This is the content"));
        arraySearch.add(new SearchModel("Set,Logic and Functions","Mathematics","IOM, IOE","This is the content of Mathematics"));
        arraySearch.add(new SearchModel("Wave Motion","Physics-Wave and Sound","IOM, IOE","This is the content"));
        arraySearch.add(new SearchModel("Direct Indirect Speech","English-Grammer","IOM, IOE","This is the content"));
        arraySearch.add(new SearchModel("General Knowledge",null,"Loksewa","This is the content"));
        arraySearch.add(new SearchModel("Intelligent Question",null,"Loksewa","This is the content"));



        RecyclerSearchAdapter adapter = new RecyclerSearchAdapter(view.getContext(),arraySearch);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void filterList(String text) {
        ArrayList<SearchModel> filteredList = new ArrayList<>();
        for(SearchModel searchModel : arraySearch){
            if(searchModel.topic.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(searchModel);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getContext(),"No Data Found",Toast.LENGTH_SHORT).show();
        }else
        {
            RecyclerSearchAdapter adapter= new RecyclerSearchAdapter(getContext(),filteredList);
            adapter.setFilteredList(filteredList);}

    }


}
