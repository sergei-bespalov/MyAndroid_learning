package sergei.bespalov.calculator;

import java.math.BigDecimal;

/**
 * Created by Sergei on 17.02.2015.
 */
public class Subtraction implements Operation {

    private final String symbol = "-";

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String execute(String arg1, String arg2) {
        Integer x = Integer.parseInt(arg1);
        Integer y = Integer.parseInt(arg2);
        Integer z = x - y;
        return z.toString();
    }

}
