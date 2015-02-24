package com.sergei.listview;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {
    public static final String EXTRA_VERSION = "com.sergei.listview.intent.extra.EXTRA_VERSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String version = intent.getStringExtra(EXTRA_VERSION);
        TextView versionView = (TextView) findViewById(R.id.version_text);
        versionView.setText(version);

    }
}
