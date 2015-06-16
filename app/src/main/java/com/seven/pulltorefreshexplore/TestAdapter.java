package com.seven.pulltorefreshexplore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyuan on 15-6-16.
 */
public class TestAdapter extends BaseAdapter{
    public List<String> mNames = new ArrayList<>();

    @Override
    public int getCount() {
        return mNames.size();
    }

    @Override
    public Object getItem(int position) {
        return mNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.list_item_test, null);

        TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText((String)getItem(position));

        return view;
    }
}
