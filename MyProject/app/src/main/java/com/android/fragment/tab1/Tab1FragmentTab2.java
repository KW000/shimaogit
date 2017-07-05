package com.android.fragment.tab1;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import com.android.R;
import com.android.fragment.BaseFragment;
import com.android.fragment.adapter.Tab1Tab2ListAdapter;
import com.android.fragment.bean.Group;
import com.android.fragment.bean.People;
import com.android.listviews.PinnedHeaderExpandableListView;
import com.android.listviews.StickyLayout;
import com.android.utils.ResourceUtils;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：flyframe on 2017/3/10 14:11
 * 邮箱：jxsm6@163.com
 * desc: 头部伸缩 列表展开listView
 */
public class Tab1FragmentTab2 extends BaseFragment implements ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener,
        StickyLayout.OnGiveUpTouchEventListener {

    private PinnedHeaderExpandableListView listView;
    private StickyLayout stickyLayout;
    private ArrayList<Group> groupList;
    private ArrayList<List<People>> childList;
    private Tab1Tab2ListAdapter adapter;
    private ImageView imageView;

    @Override
    protected void initData() {
        groupList = new ArrayList<Group>();
        Group group = null;
        for (int i = 0; i < 3; i++) {
            group = new Group();
            group.setTitle("group-" + i);
            groupList.add(group);
        }

        childList = new ArrayList<List<People>>();
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<People> childTemp =  new ArrayList<People>();;
            if (i == 0) {
                for (int j = 0; j < 13; j++) {
                    People people = new People();
                    people.setName("yy-" + j);
                    people.setAge(30);
                    people.setAddress("sh-" + j);
                    childTemp.add(people);
                }
            } else if (i == 1) {
                for (int j = 0; j < 8; j++) {
                    People people = new People();
                    people.setName("ff-" + j);
                    people.setAge(40);
                    people.setAddress("sh-" + j);
                    childTemp.add(people);
                }
            } else {
                for (int j = 0; j < 23; j++) {
                    People people = new People();
                    people.setName("hh-" + j);
                    people.setAge(20);
                    people.setAddress("sh-" + j);
                    childTemp.add(people);
                }
            }
            childList.add(childTemp);
        }
    }

    @Override
    protected int setContentLayout() {
        return R.layout.frag_tab1_tab2;
    }

    @Override
    protected void initView(View view) {
        imageView = getViewById(view,R.id.imageView1);
        stickyLayout = getViewById(view, R.id.sticky_layout);
        listView = getViewById(view, R.id.expandablelist);
        adapter = new Tab1Tab2ListAdapter(context,groupList,childList);
        listView.setAdapter(adapter);
        // 展开所有group
        for (int i = 0, count = listView.getCount(); i < count; i++) {
            listView.expandGroup(i);
        }
        listView.setOnHeaderUpdateListener(this);
        listView.setOnChildClickListener(this);
        listView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
    }


    @Override
    public boolean onGroupClick(final ExpandableListView parent, final View v,
                                int groupPosition, final long id) {

        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(context,
                childList.get(groupPosition).get(childPosition).getName(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.group, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        Group firstVisibleGroup = (Group) adapter.getGroup(firstVisibleGroupPos);
        TextView textView = (TextView) headerView.findViewById(R.id.group);
        textView.setText(firstVisibleGroup.getTitle());
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        if (listView.getFirstVisiblePosition() == 0) {
            View view = listView.getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return true;
            }
        }
        return false;
    }
}
