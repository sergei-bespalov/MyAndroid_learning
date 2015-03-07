package sergei.bespalov.phonestabletversion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sergei on 07.03.2015.
 */
public class MainActivity extends ActionBarActivity {

    private final static String TAG = "MainActivity";
    private final static String URL = "https://docs.google.com/uc?authuser=0&id=0BxyiZJDI1SglOU9sWXpLRlNsX1k&export=download";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d(TAG,"MainActivity Created");
    }

    protected class GetDataTask extends AsyncTask<Void,Void,List<Phone>>{

        @Override
        protected List<Phone> doInBackground(Void... params) {
            List<Phone> phones = getData();
            return phones;
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
                List<Phone> phones = new LinkedList<Phone>();
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
}
