package com.sergei.listview;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

    public static final  String[] ANDROID_VERSIONS={
            "Apple pie",
            "Banana Bread",
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop"
            
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //ListView listView= (ListView) findViewById(android.R.id.list);

        ArrayAdapter<String> adapter= new VersionsAdapter(this, R.layout.list_item_versions, ANDROID_VERSIONS );
        setListAdapter(adapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ver 3
                /*ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                String sel_version = adapter.getItem(position);
                invokeDetail(sel_version);*/

                // ver 4
             /*   String sel_version = (String) parent.getItemAtPosition(position);
                invokeDetail(sel_version);
            }
        });*/
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String sel_version = (String) l.getItemAtPosition(position);
        invokeDetail(sel_version);
    }

    private void invokeDetail(String version){
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_VERSION, version);
        startActivity(intent);
    }
}
