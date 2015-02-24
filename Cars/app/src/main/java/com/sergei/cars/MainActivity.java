package com.sergei.cars;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private List<Car> cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillCars();
        final ListView listView = (ListView) findViewById(R.id.list);
        CarsAdapter adapter = new CarsAdapter(this, R.layout.list_item_car, cars);
        listView.setAdapter(adapter);
        final MainActivity thisActivity = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = (Car) listView.getItemAtPosition(position);
                Intent intent = new Intent(thisActivity, DetailActivity.class);
                intent.putExtra(DetailActivity.CAR_NAME,car.name);
                intent.putExtra(DetailActivity.CAR_MAX_SPEED, String.valueOf(car.maxSpeed));
                intent.putExtra(DetailActivity.PRICE, String.valueOf(car.price));
                startActivity(intent);
            }
        });

    }

    private  void fillCars(){
        cars = new ArrayList<>();
        cars.add(new Car("BMW", 240, 2000000));
        cars.add(new Car("Audi", 240, 1000000));
        cars.add(new Car("Lada", 120, 200000));
        cars.add(new Car("Kamaz", 80, 3000000));
        cars.add(new Car("Volga", 130, 1000000));
    }
}
