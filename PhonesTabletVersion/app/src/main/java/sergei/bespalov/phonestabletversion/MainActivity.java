package sergei.bespalov.phonestabletversion;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sergei on 07.03.2015.
 */
public class MainActivity extends ActionBarActivity {

    static ListView mListView;
    private final static String TAG = "MainActivity";
    private final static String URL = "https://docs.google.com/uc?authuser=0&id=0BxyiZJDI1SglOU9sWXpLRlNsX1k&export=download";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"MainActivity onCrate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetDataTask().execute();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateDetails(position);
            }
        });
        Log.d(TAG,"MainActivity Created");
    }


    private void updateDetails(Integer position){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        DetailFragment fragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
        if(fragment != null ){
            fragment.update(position);
        }
        else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.INTENT_EXTRA_POSITION,position);
            startActivity(intent);
        }
    }

    protected class GetDataTask extends AsyncTask<Void,Void,List<Phone>>{

        @Override
        protected List<Phone> doInBackground(Void... params) {
            Log.d(TAG,"GetDataTask started");
            List<Phone> phones = getData();
            return phones;
        }

        @Override
        protected void onPostExecute(List<Phone> phones) {
            super.onPostExecute(phones);
            ArrayAdapter<Phone> adapter = new PhoneAdapter(MainActivity.this, phones);
            mListView.setAdapter(adapter);
            FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
            DetailFragment fragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
            if (fragment != null){
                fragment.update(0);
            }
        }

        protected List<Phone> getData(){
            String data = downloadData();
            return parseJson(data);
        }

        protected String downloadData(){
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(URL);
                HttpResponse response = client.execute(get);
                Scanner scanner = new Scanner(response.getEntity().getContent());
                StringBuffer buffer = new StringBuffer();
                while (scanner.hasNextLine()){
                    buffer.append(scanner.nextLine());
                }
                return buffer.toString();
            }catch (IOException e){
                Log.e(TAG,"HTTP error",e);
            }
            return "";
        }

        protected List<Phone> parseJson(String data){
            try {
                JSONObject root = new JSONObject(data);
                JSONArray arr = root.getJSONArray("phones");
                List<Phone> phones = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++){
                    JSONObject jPhone = arr.getJSONObject(i);
                    String name = jPhone.getString("name");
                    String os = jPhone.getString("os");
                    Double rating = jPhone.getDouble("rating");
                    Integer price = jPhone.getInt("price");
                    phones.add(new Phone(name,os,rating,price));
                }
                return phones;

            } catch (JSONException e) {
                Log.e(TAG,"JSON parsing error",e);
            }
            return null;
        }
    }

    public static class ListFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_list, container, false);
            mListView = (ListView) view.findViewById(R.id.view_list);
            Log.d(TAG, "ListFtagment onCreate View");
            return view;
        }
    }

    public static class DetailFragment extends Fragment{
        private View view;
        private TextView title;
        private TextView rating;
        private TextView os;
        private TextView price;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.view = inflater.inflate(R.layout.fragment_detail, container, false);
            Log.d(TAG, "DetailFragment");
            return view;
        }

        public void update(int position){
            Log.d(TAG, "Fragment updated");
            if (view == null) return;

            Phone phone = (Phone) mListView.getItemAtPosition(position);

            if (title == null || rating == null || os == null || price == null){

                title = (TextView) view.findViewById(R.id.detail_title);
                rating = (TextView) view.findViewById(R.id.detail_rating_value);
                os = (TextView) view.findViewById(R.id.detail_os_value);
                price = (TextView) view.findViewById(R.id.detail_price_value);
            }

            title.setText(phone.name);
            rating.setText(phone.rating.toString());
            os.setText(phone.os);
            price.setText(phone.price.toString());
        }
    }
}
