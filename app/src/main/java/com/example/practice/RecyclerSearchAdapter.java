package com.example.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchModel> arraySearch;

    public void setFilteredList(ArrayList<SearchModel> filteredList){
        this.arraySearch=filteredList;
        notifyDataSetChanged();
    }

    RecyclerSearchAdapter(Context context, ArrayList<SearchModel> arraySearch){
        this.context=context;
        this.arraySearch=arraySearch;
    }
    @NonNull
    @Override
    public RecyclerSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_row,parent,false);
        RecyclerSearchAdapter.ViewHolder viewHolder = new RecyclerSearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSearchAdapter.ViewHolder holder, int position) {
        holder.topic.setText(arraySearch.get(position).topic);
        holder.topicParent.setText(arraySearch.get(position).topicParent);
        holder.courses.setText(arraySearch.get(position).courses);

    }

    @Override
    public int getItemCount() {
        return arraySearch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView topic,topicParent,courses;

        public ViewHolder(View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
            topicParent = itemView.findViewById(R.id.topicParent);
            courses = itemView.findViewById(R.id.courses);

        }
    }
}
