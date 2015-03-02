package com.sergei.httptest;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends ActionBarActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_cars);
        findViewById(R.id.button_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataTask task = new GetDataTask();
                task.execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetDataTask extends AsyncTask<Void,Void,List<Car>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Car> doInBackground(Void... params) {
            return loadCars();
        }

        @Override
        protected void onPostExecute(List<Car> s) {
            super.onPostExecute(s);
            CarsAdapter carsAdapter = new CarsAdapter(MainActivity.this, s);
            mListView.setAdapter(carsAdapter);
        }

        private List<Car> loadCars(){
            String s = getData();
            return parseJson(s);
        }

        private String getData(){
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet("https://docs.google.com/uc?authuser=0&id=0BxyiZJDI1SglSU5BZWQyWEZhZUk&export=download");
                HttpResponse response = client.execute(request);
                InputStream is = response.getEntity().getContent();
                Scanner scanner = new Scanner(is);
                StringBuilder sb = new StringBuilder();
                while (scanner.hasNextLine()){
                    sb.append(scanner.nextLine()+"\n");
                }
                return sb.toString();
            }catch (IOException e){
                Log.e("GetDataTask", "HTTP error ", e);
            }
            return "";
        }

        private List<Car> parseJson(String jsonStr){
            List<Car> cars = new ArrayList<>();
            if(jsonStr != null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    //получить массив машин
                    JSONArray carsArr = jsonObject.getJSONArray("cars");

                    //перебираем массив машин
                    for (int i=0; i<carsArr.length();i++){
                        JSONObject carJ = carsArr.getJSONObject(i);
                        String name = carJ.getString("name");
                        String vin = carJ.getString("vin");
                        Car car = new Car(name,vin);
                        cars.add(car);

                        //Log.d("MainActivity", "name: " + name+", vin: "+vin);
                    }

                }catch (JSONException e){
                    Log.e("MainActivity", "Error parse JSON", e);
                }
            }
            return cars;
        }
    }

}
