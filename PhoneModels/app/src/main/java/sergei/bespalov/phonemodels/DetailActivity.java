package sergei.bespalov.phonemodels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Sergei on 04.03.2015.
 */
public class DetailActivity extends ActionBarActivity {
    public  static  final  String INTENT_EXTRA_PHONE = "sergei.bespalov.phonemodels.intent.exetra.phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Phone phone = (Phone) intent.getSerializableExtra(INTENT_EXTRA_PHONE);

        TextView name = (TextView) findViewById(R.id.text_name);
        TextView rating = (TextView) findViewById(R.id.text_rating);
        TextView price = (TextView) findViewById(R.id.text_price);
        TextView os = (TextView) findViewById(R.id.text_os);

        name.setText(phone.name);
        rating.setText(phone.rating.toString());
        price.setText(phone.price.toString());
        os.setText(phone.os);
    }
}
