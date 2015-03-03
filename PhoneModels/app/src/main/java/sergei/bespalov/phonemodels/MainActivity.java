package sergei.bespalov.phonemodels;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends ActionBarActivity {

    private static final String DATA_LINK = "https://docs.google.com/uc?authuser=0&id=0BxyiZJDI1SglOU9sWXpLRlNsX1k&export=download";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_phones);
        findViewById(R.id.button_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetDataTask().execute();
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

    private class GetDataTask extends AsyncTask<Void,Void,List<Phone>>{

        @Override
        protected List<Phone> doInBackground(Void... params) {
            return downloadPhones();
        }

        @Override
        protected void onPostExecute(final List<Phone> phones) {
            super.onPostExecute(phones);
            AdapterPhone adapter = new AdapterPhone(MainActivity.this, phones);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    invokeDetailActivity((Phone) parent.getItemAtPosition(position));
                }
            });

        }

        private void invokeDetailActivity(Phone phone){
            Intent intent = new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra(DetailActivity.INTENT_EXTRA_PHONE, phone);
            startActivity(intent);
        }

        private List<Phone> downloadPhones(){
            String data = downloadData();
            return parseJson(data);
        }

        private String downloadData(){
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(DATA_LINK);
                HttpResponse response = client.execute(request);
                InputStream stream = response.getEntity().getContent();
                /*URL url = new URL(DATA_LINK);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());*/
                Scanner scanner = new Scanner(stream);
                StringBuffer buffer = new StringBuffer();
                while (scanner.hasNextLine()){
                    buffer.append(scanner.nextLine());
                }
                Log.d("MainActivity", buffer.toString());
                stream.close();
                return buffer.toString();
            }catch (IOException e){
                Log.e("MainActivity", "Http error",e);
            }
            return "";
        }

        private List<Phone> parseJson(String data){
            List<Phone> phones = new ArrayList<Phone>();
            try {
                JSONObject root = new JSONObject(data);
                JSONArray phonesArr = root.getJSONArray("phones");
                for (int i = 0; i < phonesArr.length(); i++){
                    JSONObject jPhone = phonesArr.getJSONObject(i);
                    String name = jPhone.getString("name");
                    Double rating = jPhone.getDouble("rating");
                    String os = jPhone.getString("os");
                    Integer price = jPhone.getInt("price");
                    Phone phone = new Phone(name,rating,os,price);
                    phones.add(phone);
                }
                Log.d("MainActivity", "отпарсен l=" + phones.size());
            }catch (JSONException e){
                Log.e("MainActivity", "Parse JSON error",e);
            }
            return phones;
        }


    }


}
