package duke;

/**
 * The class to handle the WrongMessage or Confusing messages.
 *
 * @author Lan Jingbo, Jerry
 */
public class WrongMessageException extends Exception{

    /**
     * The constructor with no input.
     */
    public WrongMessageException() {
        super("☹ OOPS!!! Please check whether you type correctly");
    }

    /**
     * The constructor with input.
     * @param s The input.
     */
    public WrongMessageException(String s) {
        super(s);
    }

}
