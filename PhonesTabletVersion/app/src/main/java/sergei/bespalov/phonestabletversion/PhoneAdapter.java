package sergei.bespalov.phonestabletversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Sergei on 08.03.2015.
 */
public class PhoneAdapter extends ArrayAdapter<Phone> {
    private LayoutInflater inflater;

    public PhoneAdapter(Context context,List objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = inflater.inflate(R.layout.item_phone,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.item_phone_name);
            view.setTag(holder);
        }else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        Phone phone = (Phone) getItem(position);
        holder.name.setText(phone.name);
        return view;
    }

    class ViewHolder{
        TextView name;
    }
}
