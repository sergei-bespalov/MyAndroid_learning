package sergei.bespalov.phonestabletversion;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by Sergei on 08.03.2015.
 */
public class DetailActivity extends ActionBarActivity {
    public static final String INTENT_EXTRA_POSITION = "sergei.bespalov.phonestabletversion.intent.extra.position";
    public static final String TAG = "DetailActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate called");
        setContentView(R.layout.activity_detail);
        MainActivity.DetailFragment fragment = (MainActivity.DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detail_fragment);
        Intent intent = getIntent();
        Integer position = intent.getIntExtra(INTENT_EXTRA_POSITION,1);
        fragment.update(position);
    }
}
