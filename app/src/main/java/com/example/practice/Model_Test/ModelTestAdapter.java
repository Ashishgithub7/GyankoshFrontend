package com.example.practice.Model_Test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.practice.Model_Test.Model_test_model;
import com.example.practice.R;

import java.util.List;

public class ModelTestAdapter extends ArrayAdapter<Model_test_model> {

    public ModelTestAdapter(Context context, List<Model_test_model> models) {
        super(context, 0, models);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Model_test_model modelSet = getItem(position);
        if (modelSet == null) {
            // Handle the case where the model is null (this shouldn't happen)
            throw new NullPointerException("Model_test_model is null at position: " + position);
        }
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_set_row, parent, false);
        }
        // Lookup view for data population
        TextView modelSetName = convertView.findViewById(R.id.modelSetName);
        // Populate the data into the template view using the data object
        modelSetName.setText(modelSet.getSetName());
        // Return the completed view to render on screen
        return convertView;
    }
}
