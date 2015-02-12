package com.sergei.helloworld;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //paret conteiner
        LinearLayout parentContainer = new LinearLayout(this);
        parentContainer.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        parentContainer.setPadding(16, 16, 16, 16);
        parentContainer.setOrientation(LinearLayout.VERTICAL);

       //create address container
        LinearLayout addressContainer = new LinearLayout(this);
        addressContainer.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        addressContainer.setOrientation(LinearLayout.VERTICAL);
        addressContainer.setPadding(0,8,0,0);

        TextView addressLabel = new TextView(this);
        addressLabel.setText("Address");

        TextView addressValue = new TextView(this);
        addressValue.setText("Saint Petersburg");

        addressContainer.addView(addressLabel);
        addressContainer.addView(addressValue);

        //name container
        LinearLayout nameContainer = new LinearLayout(this);
        nameContainer.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        nameContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView nameLabel = new TextView(this);
        nameLabel.setText("Name");

        TextView nameValue = new TextView(this);
        nameValue.setText("Sergei Bespalov");

        nameContainer.addView(nameLabel);
        nameContainer.addView(nameValue);

        parentContainer.addView(nameContainer);
        parentContainer.addView(addressContainer);

        setContentView(parentContainer);



       // setContentView(R.layout.activity_main);
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
}
