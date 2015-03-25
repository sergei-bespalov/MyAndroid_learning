package bespalov.sergei.viewpagerfirst;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by sergei on 25.03.15.
 */
public class FragmentEditor extends Fragment {

    public static final String KEY_POSITION = "bespalov.sergei.viewpagerfirst.KEY_POSITION";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editor, container, false);
        EditText editText = (EditText) view.findViewById(R.id.editor);
        int position = getArguments().getInt(KEY_POSITION);
        editText.setHint(getString(R.string.editor)+String.valueOf(position+1));
        return view;
    }

    public static FragmentEditor newInstance(int position){
        FragmentEditor frag = new FragmentEditor();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION,position);
        frag.setArguments(args);

        return frag;
    }

}
