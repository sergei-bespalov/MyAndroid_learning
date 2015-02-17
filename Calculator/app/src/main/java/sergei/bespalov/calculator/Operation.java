package sergei.bespalov.calculator;

/**
 * Created by Sergei on 17.02.2015.
 */
public interface Operation {

    /**
     * get operation symbol
     * @return operation symbol
     */
    public String getSymbol();

    /**
     * execute operation
     * @param arg1 left number
     * @param arg2 right number
     * @return operation result
     */
    public String execute(String arg1, String arg2);

}
