package sergei.bespalov.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergei on 08.01.2015.
 */
public class DataTime extends Fragment {
    private String time;
    private static String TAG_DATE_TIME = "datatime";

    /**
     * this method called when fragment created
     *
     * @param state
     */
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        /*if (null != state) {
            time = state.getString(TAG_DATE_TIME);
        }
        if (null == time) {
            time = new SimpleDateFormat("d MM yyyy HH:mm:ss").format(new Date());
        }*/
        if (null == state) {state = getArguments();}
        if (null != state) {time = state.getString(TAG_DATE_TIME);}
        if (null == time) {time = getDateTimeString(new Date());}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View view = inflater.inflate(R.layout.date_time, container, false);
        ((TextView) view.findViewById(R.id.last_view_time)).setText(time);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(TAG_DATE_TIME, time);
    }

    /**
     * crating an object DataTime
     * @param time displayed time
     * @return object DataTime
     */
    public static DataTime createInstance(Date time){
        Bundle init = new Bundle();
        init.putString(
                DataTime.TAG_DATE_TIME,
                getDateTimeString(time)
        );
        DataTime frag = new DataTime();
        frag.setArguments(init);
        return frag;
    }

    /**
     * get formatted data Data
     * @param time displayed data
     * @return formatted data
     */
    private static String getDateTimeString(Date time) {
        return new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(time);
    }

}
