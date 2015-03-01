package sergei.bespalov.mysettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = (TextView) findViewById(R.id.hello);
        textView.setText(getAllPref());
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getAllPref(){
        StringBuilder sb = new StringBuilder();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        sb.append("checkbox_1 = " + String.valueOf(pref.getBoolean(SettingsActivity.KEY_CHBOX_1,false)));
        sb.append("\npref_list = "+ pref.getString(SettingsActivity.KEY_LIST,"default"));
        sb.append("\npref_text = "+ pref.getString(SettingsActivity.KEY_TEXT,"default"));
        sb.append("\npref_ring = " + pref.getString(SettingsActivity.KEY_RING,"lalala"));
        sb.append("\npref_text_2 = "+pref.getString(SettingsActivity.KEY_TEXT_2,"default"));
        return  sb.toString();
    }
}
