package com.sergei.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Student_03 on 24.02.2015.
 */
public class VersionsAdapter extends ArrayAdapter<String> {

    public VersionsAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = LayoutInflater.from( parent.getContext()).inflate(R.layout.list_item_versions,parent,false);
        String version = getItem(position);
        TextView versionView = (TextView) view.findViewById(R.id.text);
        versionView.setText(version);
        return view;
    }
}
