package com.sergei.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Student_03 on 09.03.2015.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private final LayoutInflater inflater;
    private final Picasso picasso;

    public NewsAdapter(Context context, List objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
        picasso = Picasso.with(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = inflater.inflate(R.layout.list_item_news, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.image);
            holder.textView = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        }else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        News news = getItem(position);
        holder.textView.setText(news.title);
        picasso.load(news.image).into(holder.imageView);
        return view;
    }

    protected class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
