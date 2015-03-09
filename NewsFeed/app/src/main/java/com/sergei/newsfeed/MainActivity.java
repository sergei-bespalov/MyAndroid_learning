package com.sergei.newsfeed;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.news_list);
        new NewsLoader().execute();
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

    public class NewsLoader extends AsyncTask <Void,Void,List<News>>{

        @Override
        protected List<News> doInBackground(Void... params) {
            String jsonUrl = "https://docs.google.com/uc?authuser=0&id=0BxyiZJDI1SglU013U08ydXkzSm8&export=download";
            try {
                return parseJson(loadJson(jsonUrl));
            }catch (Exception e){
                Log.e("MainActivity", "Download task error", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<News> newses) {
            super.onPostExecute(newses);
            ListAdapter adapter = new NewsAdapter(MainActivity.this, newses);
            mListView.setAdapter(adapter);
        }

        private String loadJson(String url) throws IOException {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            Scanner scanner = new Scanner(response.getEntity().getContent());
            StringBuffer buffer = new StringBuffer();
            while (scanner.hasNextLine()){
                buffer.append(scanner.nextLine());
            }
            return buffer.toString();

        }

        private List<News> parseJson(String json) throws JSONException {
            List<News> newsList = new ArrayList<>();
            JSONObject root = new JSONObject(json);
            JSONArray array = root.getJSONArray("news");
            for(int i = 0; i< array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                Integer id = object.getInt("id");
                String iUrl = object.getString("image_url");
                String title = object.getString("title");
                String description = object.getString("description");

                News news = new News(id,title,description,iUrl);
                newsList.add(news);
            }
            return newsList;
        }
    }
}
