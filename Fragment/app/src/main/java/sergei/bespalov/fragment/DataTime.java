package sergei.bespalov.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergei on 08.01.2015.
 */
public class DataTime extends Fragment {
    private String time;

    /**
     * this method called when fragment created
     * @param state
     */
    public void onCreate(Bundle state){
        super.onCreate(state);
        if (null == time) {
            time = new SimpleDateFormat("d MM yyyy HH:mm:ss").format(new Date());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        View view = inflater.inflate(R.layout.data_time, container, false);
        ((TextView)view.findViewById(R.id.last_view_time)).setText(time);
        return view;
    }

}
