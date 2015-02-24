package com.sergei.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Student_03 on 24.02.2015.
 */
public class CarsAdapter extends ArrayAdapter<Car>{

    public CarsAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_car,parent,false);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView maxSpeedView = (TextView) view.findViewById(R.id.max_speed);
        TextView priceView = (TextView) view.findViewById(R.id.price);

        Car car = getItem(position);

        nameView.setText(car.name);
        maxSpeedView.setText(String.valueOf(car.maxSpeed));
        priceView.setText(String.valueOf(car.price));
        return view;
    }
}
