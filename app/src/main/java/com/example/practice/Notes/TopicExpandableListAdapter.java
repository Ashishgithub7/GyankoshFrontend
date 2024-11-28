package com.example.practice.Notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.practice.Entity.NoteTopic;
import com.example.practice.R;

import java.util.List;

public class TopicExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<NoteTopic> topics;

    public TopicExpandableListAdapter(Context context, List<NoteTopic> topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public int getGroupCount() {
        return topics.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return topics.get(groupPosition).getSubtopics().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return topics.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return topics.get(groupPosition).getSubtopics().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String topicName = ((NoteTopic) getGroup(groupPosition)).getTopicName();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notes_topic_layout, null);
        }
        TextView topicTextView = convertView.findViewById(R.id.topic);
        topicTextView.setText(topicName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String subtopicName = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notes_subtopic_layout, null);
        }
        TextView subtopicTextView = convertView.findViewById(R.id.subTopic);
        subtopicTextView.setText(subtopicName);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
