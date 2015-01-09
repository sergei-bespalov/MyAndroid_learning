package sergei.bespalov.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Date;


public class FragmentActivity extends ActionBarActivity {
    static String FRAG1_TAG = "FRAG1_TAG";
    static String FRAG2_TAG = "FRAG2_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fM = getFragmentManager();
        FragmentTransaction xact = fM.beginTransaction();

        if (null == fM.findFragmentByTag(FRAG1_TAG)) {
            xact.add(
                    R.id.date_time,
                    DataTime.createInstance(new Date()),
                    FRAG1_TAG
            );
        }

        if (null == fM.findFragmentByTag(FRAG2_TAG)) {
            xact.add(
                    R.id.date_time_2,
                    DataTime.createInstance(new Date()),
                    FRAG2_TAG
            );
        }

        xact.commit();

        findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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

    /**
     * update displayed date
     */
    void update(){
        Date time = new Date();

        FragmentTransaction xact = getFragmentManager().beginTransaction();

        xact.replace(
                R.id.date_time,
                DataTime.createInstance(time),
                FRAG1_TAG);

        xact.replace(
                R.id.date_time_2,
                DataTime.createInstance(time),
                FRAG1_TAG
        );

        xact.addToBackStack(null);
        xact.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        xact.commit();
    }
}
