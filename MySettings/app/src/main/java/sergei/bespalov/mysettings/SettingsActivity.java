package sergei.bespalov.mysettings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import java.util.List;

/**
 * Created by Sergei on 01.03.2015.
 */
public class SettingsActivity extends PreferenceActivity {

    public static final String KEY_CHBOX_1 = "checkbox1";
    public static final String KEY_LIST = "pref_list";
    public static final String KEY_TEXT = "pref_text";
    public static final String KEY_RING = "pref_ring";
    public static final String KEY_TEXT_2 = "pref_text_2";

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.preference_headers,target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        if (Display.class.getName().equals(fragmentName)){
            return true;
        }
        return false;
    }

    public static class Display extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_display);
        }
    }
}
