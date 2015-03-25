package bespalov.sergei.pageviewfirst;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by sergei on 25.03.15.
 */
public class SampleAdapter extends FragmentPagerAdapter{
    public SampleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentEditor.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }
}
