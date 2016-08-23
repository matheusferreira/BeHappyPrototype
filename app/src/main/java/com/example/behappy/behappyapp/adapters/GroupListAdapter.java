package com.example.behappy.behappyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.classes.Ingredient;
import com.example.behappy.behappyapp.classes.News;
import com.example.behappy.behappyapp.classes.Nutrient;
import com.example.behappy.behappyapp.classes.Product;

import java.util.ArrayList;

/**
 * Created by Matheus on 01/07/2016.
 */
public class GroupListAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem;
    public ArrayList<Ingredient> tempChildIngredient;
    public ArrayList<Object> childItem = new ArrayList<Object>();
    public LayoutInflater mInflater;
    public Context mContext;

    public GroupListAdapter(ArrayList<String> grList, ArrayList<Object> childItem){
        groupItem = grList;
        this.childItem = childItem;
    }

    public void setInflater (LayoutInflater mInflater, Context context){
        this.mInflater = mInflater;
        this.mContext = context;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        tempChildIngredient = (ArrayList<Ingredient>) childItem.get(groupPosition);
        //if (convertView == null) {
            convertView = mInflater.inflate(R.layout.expandable_list_groups_item, null);
        //}
        TextView ingredientName = (TextView) convertView.findViewById(R.id.ingredient_name_group);
        ingredientName.setText(tempChildIngredient.get(childPosition).getName());



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, tempChildIngredient.get(childPosition).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });



        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childItem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.expandable_list_groups_header, null);
        }
        ((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
