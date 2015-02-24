package com.sergei.cars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Sergei on 25.02.2015.
 */
public class DetailActivity extends ActionBarActivity {
    public static final String CAR_NAME = "com.sergei.intent.cars.CAR_NAME";
    public static final String CAR_MAX_SPEED = "com.sergei.intent.cars.CAR_MAX_SPEED";
    public static final String PRICE = "com.sergei.intent.cars.PRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(CAR_NAME);
        String speed = intent.getStringExtra(CAR_MAX_SPEED);
        String price = intent.getStringExtra(PRICE);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(name);
        TextView max_speed = (TextView) findViewById(R.id.value_max_speed);
        max_speed.setText(speed);
        TextView value_price = (TextView) findViewById(R.id.value_price);
        value_price.setText(price);
    }
}
