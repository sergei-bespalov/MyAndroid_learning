package sergei.bespalov.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sergei on 22.02.2015.
 */
public class InfoActivity extends Activity {

    public static final String USER_NAME = "sergei.bespalov.activitytransition.USER_NAME";
    public static final String USER_AGE = "sergei.bespalov.activitytransition.USER_AGE";
    public static final String USER_ADDRESS = "sergei.bespalov.activitytransition.USER_ADDRESS";
    public static final String USER_EMAIL = "sergei.bespalov.activitytransition.USER_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final Intent intent = getIntent();

        final String name = intent.getStringExtra(USER_NAME);
        String age = intent.getStringExtra(USER_AGE);
        String address = intent.getStringExtra(USER_ADDRESS);
        final String email = intent.getStringExtra(USER_EMAIL);

        if (name != null){
            TextView nameView = (TextView) findViewById(R.id.result_name);
            nameView.setText(name);
        }

        if (age != null){
            TextView ageView = (TextView) findViewById(R.id.result_age);
            ageView.setText(age);
        }

        if (address != null){
            TextView addressView = (TextView) findViewById(R.id.result_addr);
            addressView.setText(address);
        }

        if (email != null){
            TextView emailView = (TextView) findViewById(R.id.result_email);
            emailView.setText(email);
        }

        findViewById(R.id.button_send_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(intent.EXTRA_EMAIL,email);
                intent.putExtra(intent.EXTRA_TEXT, "Hello " + name);
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(intent.EXTRA_EMAIL,new String[]{email});
                intent.putExtra(intent.EXTRA_TEXT, "Hello " + name);
                startActivity(intent);
            }
        });


    }
}
