package sergei.bespalov.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Sergei on 22.02.2015.
 */
public class EditActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViewById(R.id.button_save_user).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                invokeEditActivity();
            }
        });
    }

    protected void invokeEditActivity(){
        Intent intent = new Intent(this, InfoActivity.class);

        String name = String.valueOf(((EditText)findViewById(R.id.edit_name)).getText());
        String age = String.valueOf(((EditText)findViewById(R.id.edit_age)).getText());
        String address = String.valueOf(((EditText)findViewById(R.id.edit_address)).getText());
        String email = String.valueOf(((EditText)findViewById(R.id.edit_email)).getText());

        intent.putExtra(InfoActivity.USER_NAME, name);
        intent.putExtra(InfoActivity.USER_AGE, age);
        intent.putExtra(InfoActivity.USER_ADDRESS, address);
        intent.putExtra(InfoActivity.USER_EMAIL,email);

        startActivity(intent);
    }
}
