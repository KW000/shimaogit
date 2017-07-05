package com.android.fragment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.R;
import com.android.fragment.bean.Group;
import com.android.fragment.bean.People;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：flyframe on 2017/4/7 10:47
 * 邮箱：jxsm6@163.com
 */
public class Tab1Tab2ListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Group> groupList;
    private ArrayList<List<People>> childList;

    public Tab1Tab2ListAdapter(Context context,ArrayList<Group> groupList,ArrayList<List<People>> childList) {
        this.context = context;
        this.childList = childList;
        this.groupList = groupList;
        inflater = LayoutInflater.from(context);
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.group, null);
            groupHolder.textView = (TextView) convertView
                    .findViewById(R.id.group);
            groupHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.image);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.textView.setText(((Group)groupList.get(groupPosition)).getTitle());
        if (isExpanded)// ture is Expanded or false is not isExpanded
            groupHolder.imageView.setImageResource(R.drawable.expanded);
        else
            groupHolder.imageView.setImageResource(R.drawable.collapse);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.child, null);

            childHolder.textName = (TextView) convertView
                    .findViewById(R.id.name);
            childHolder.textAge = (TextView) convertView
                    .findViewById(R.id.age);
            childHolder.textAddress = (TextView) convertView
                    .findViewById(R.id.address);
            childHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.image);
            Button button = (Button) convertView
                    .findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicked pos=", Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.textName.setText(((People) getChild(groupPosition,
                childPosition)).getName());
        childHolder.textAge.setText(String.valueOf(((People) getChild(
                groupPosition, childPosition)).getAge()));
        childHolder.textAddress.setText(((People) getChild(groupPosition,
                childPosition)).getAddress());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        TextView textView;
        ImageView imageView;
    }

    class ChildHolder {
        TextView textName;
        TextView textAge;
        TextView textAddress;
        ImageView imageView;
    }

}
