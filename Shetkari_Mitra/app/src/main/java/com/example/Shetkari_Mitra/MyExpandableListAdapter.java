package com.example.Shetkari_Mitra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, List<ChildItem4>> listHashMap1;
    private List<String> listDataHeader1;

    public MyExpandableListAdapter(Context context, List<String> listDataHeader1,
                                   HashMap<String, List<ChildItem4>> listHashMap1) {
        this.context = context;
        this.listDataHeader1 = listDataHeader1;
        this.listHashMap1 = listHashMap1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listHashMap1.get(this.listDataHeader1.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildItem4 childItem4 = (ChildItem4) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item_layout4, null);
        }
        TextView childDescriptionTextView = convertView.findViewById(R.id.childDescriptionTextView4);
        TextView childLinksTextView = convertView.findViewById(R.id.childLinksTextView4);

        // Set the description and links to their respective TextViews
        childDescriptionTextView.setText(childItem4.getDescription());
        childLinksTextView.setText(childItem4.getLinks());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listHashMap1.get(this.listDataHeader1.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader1.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader1.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item_layout4, null);
        }
        TextView groupTextView = convertView.findViewById(R.id.groupTitleTextView4);
        groupTextView.setText(groupTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
