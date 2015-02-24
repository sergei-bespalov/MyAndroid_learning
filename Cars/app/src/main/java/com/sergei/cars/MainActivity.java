package com.sergei.cars;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        ListView listView = (ListView) findViewById(R.id.list);
        CarsAdapter adapter = new CarsAdapter(this, R.layout.list_item_car, cars);
        listView.setAdapter(adapter);

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
