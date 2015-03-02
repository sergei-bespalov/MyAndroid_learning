package com.sergei.httptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sergei on 02.03.15.
 */
public class CarsAdapter extends ArrayAdapter<Car> {
    private LayoutInflater mInflater;

    public CarsAdapter(Context context, List<Car> objects){
        super(context,0,objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = mInflater.inflate(R.layout.item_car,parent,false);

            ViewHolder holder = new ViewHolder();

            holder.name = (TextView) view.findViewById(R.id.text_car);
            holder.name = (TextView) view.findViewById(R.id.text_vin);
            view.setTag(holder);
        }else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        Car car = getItem(position);

        holder.name.setText(car.name);
        holder.vin.setText(car.vin);

        return view;
    }

    static class ViewHolder{
        TextView name;
        TextView vin;
    }
}
