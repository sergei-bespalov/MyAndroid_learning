package dots.sergei.bespalov.dots;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;


public class DotsActivity extends ActionBarActivity {

    final int DIAMETER = 10;
    final Random rnd = new Random();
    final Dots dots = new Dots();
    final DotsActivity dotsActivity = this;
    DotGenerator dotGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        final DotsView dotsView = (DotsView) findViewById(R.id.dotsView);
        dotsView.setDots(dots);
        final EditText t1 = (EditText) findViewById(R.id.text1);
        final EditText t2 = (EditText) findViewById(R.id.text2);
        final Button redButton = (Button) findViewById(R.id.redButton);
        final Button greenButton = (Button) findViewById(R.id.greenButton);
        dots.setOnChangeListener(new Dots.DotChangeListener() {
            @Override
            public void onChange(Dots dot) {
                Dot last = dot.getLastDot();
                t1.setText((null == last) ? "" : last.getX().toString());
                t2.setText((null == last) ? "" : last.getY().toString());
                dotsView.invalidate();
            }
        });
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDot(
                        dots,
                        dotsView,
                        Color.RED);
            }
        });
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDot(
                        dots,
                        dotsView,
                        Color.GREEN);
            }
        });
        dotsView.setOnTouchListener(new TrackingTouchListener(dots));
        t2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /*Why DotsView not receives focus?*/
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("DotsGenerator", "Focus Change");
                if (!hasFocus && (null != dotGenerator)) {
                    dotGenerator.done();
                    dotGenerator = null;
                } else if (hasFocus && (dotGenerator == null)) {
                    dotGenerator = new DotGenerator(dots, dotsView, Color.BLACK, dotsActivity);
                    new Thread(dotGenerator).start();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dots, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_clear) {
            dots.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void addDot(Dots dots, DotsView dotsView, int color) {
        int pad = (DIAMETER + 2) * 2;
        float x = DIAMETER + (rnd.nextFloat() * (dotsView.getWidth() - pad));
        float y = DIAMETER + (rnd.nextFloat() * (dotsView.getHeight() - pad));
        dots.addDot(x, y, color, DIAMETER);
    }
}
