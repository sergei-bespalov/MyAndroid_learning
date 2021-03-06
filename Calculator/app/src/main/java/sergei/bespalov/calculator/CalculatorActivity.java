package sergei.bespalov.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalculatorActivity extends ActionBarActivity implements View.OnClickListener {

    private static String displayInfo = "";
    private static final String displayDefault = "0";
    private static String leftNumber = "";
    private static String rightNumber = "";
    private static Operation operation;
    private static Boolean needClear = false;
    private static Boolean isRight = false;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        display = (TextView) findViewById(R.id.num_display);
        display.setText(displayDefault);
        setOnClickListeners();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (needClear) clear();
        int id = v.getId();
        switch (id) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                add(id);
                break;
            case R.id.button_comma:
                break;
            case R.id.button_plus:
            case R.id.button_minus:
            case R.id.button_div:
            case R.id.button_multi:
                setOperation(id);
                break;
            case R.id.button_clear:
                clear();
                break;
            case R.id.button_clear_last:
                clearLast();
                break;
            case R.id.button_result:
                result();
                break;

        }

    }

    /**
     * calculate the result
     */
    protected void result() {
        if (operation == null) return;
        String result = operation.execute(leftNumber, rightNumber);
        operation = null;
        isRight = false;
        leftNumber = result;
        rightNumber = "";
        displayInfo = leftNumber;
        display.setText(displayInfo);
    }

    /**
     * clear display and data
     */
    protected void clear() {
        displayInfo = "";
        operation = null;
        leftNumber = "";
        rightNumber = "";
        needClear = false;
        isRight = false;
        display.setText(displayDefault);
    }

    protected void clearLast(){
        if(isRight){
            rightNumber = "";
            displayInfo = leftNumber + "\n"+operation.getSymbol() + "\n";
            display.setText(displayInfo);
        }else {
            leftNumber = "";
            displayInfo = "";
            display.setText(displayDefault);
        }

    }

    /**
     * add to display text
     *
     * @param id button id
     */
    protected void add(int id) {
        Button b = (Button) findViewById(id);
        if (isRight) {
            rightNumber = rightNumber + b.getText();
        } else {
            leftNumber = leftNumber + b.getText();
        }
        displayInfo =displayInfo + b.getText();
        display.setText(displayInfo);
    }

    /**
     * set operation
     *
     * @param id button id
     */
    protected void setOperation(int id) {
        if (leftNumber.equals("")) return;
        if (operation != null) {
            if (!rightNumber.equals("")) {
                leftNumber = operation.execute(leftNumber, rightNumber);
                rightNumber = "";
                isRight = true;
                displayInfo = leftNumber;
                operation = null;
            }else {
                displayInfo = leftNumber;
            }
        }
        if (id == R.id.button_plus) operation = new Addition();
        if (id == R.id.button_minus) operation = new Subtraction();
        if (id == R.id.button_multi) operation = new Multiplication();
        if (id == R.id.button_div) operation = new Division();
        isRight = true;
        displayInfo = displayInfo + "\n" + operation.getSymbol() + "\n";
        display.setText(displayInfo);
    }

    /**
     * set listeners
     */
    protected void setOnClickListeners() {
        findViewById(R.id.button_0).setOnClickListener(this);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);
        findViewById(R.id.button_comma).setOnClickListener(this);
        findViewById(R.id.button_clear).setOnClickListener(this);
        findViewById(R.id.button_clear_last).setOnClickListener(this);
        findViewById(R.id.button_multi).setOnClickListener(this);
        findViewById(R.id.button_div).setOnClickListener(this);
        findViewById(R.id.button_minus).setOnClickListener(this);
        findViewById(R.id.button_plus).setOnClickListener(this);
        findViewById(R.id.button_result).setOnClickListener(this);
    }
}
