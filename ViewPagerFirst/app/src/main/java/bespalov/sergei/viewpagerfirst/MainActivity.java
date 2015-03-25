package bespalov.sergei.viewpagerfirst;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by sergei on 25.03.15.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SampleAdapter(getFragmentManager()));
    }
}
