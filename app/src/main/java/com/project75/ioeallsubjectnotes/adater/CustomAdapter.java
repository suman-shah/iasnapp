package com.project75.ioeallsubjectnotes.adater;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.model.Chapter;

import java.util.List;

public class CustomAdapter implements ExpandableListAdapter {

    List<Chapter> chapterList;
    Context context;

    public CustomAdapter(List<Chapter> chapterList, Context context) {
        this.chapterList = chapterList;
        this.context = context;
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return chapterList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chapterList.get(groupPosition).getTopicsList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return chapterList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chapterList.get(groupPosition).getTopicsList().get(childPosition);
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView=layoutInflater.from(context).inflate(R.layout.chapter_item,parent,false);
        return convertView;
        TextView chapterName=(TextView)convertView.findViewById(R.id.chapterTitle);
        chapterName.setText(chapterList.get(groupPosition).getChapterName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView=layoutInflater.from(context).inflate(R.layout.topics_item,parent,false);
        TextView topicName=(TextView)convertView.findViewById(R.id.topicTitle);
        topicName.setText(chapterList.get(groupPosition).getTopicsList().get(childPosition).getTopicName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
