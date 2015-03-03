package sergei.bespalov.phonemodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sergei on 04.03.2015.
 */
public class AdapterPhone extends ArrayAdapter<Phone> {

    LayoutInflater mInflater;

    public AdapterPhone(Context context,  List objects) {
        super(context,0, objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = mInflater.inflate(R.layout.list_item_phone,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.text_title);
            view.setTag(holder);
        }else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        String title = getItem(position).name;
        holder.name.setText(title);
        return view;
}

    private class ViewHolder{
        TextView name;
    }
}
