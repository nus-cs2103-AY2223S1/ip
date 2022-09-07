package duke;

/**
 * User Interface for the Duke Chat Robot.
 *
 * @author Liu Han
 */
public class Ui {
    private final static String BREAK = "    ____________________________________________________________";

    /**
     * Welcome Message: greet the user
     */
    public void showWelcome() {
        System.out.println(BREAK +
                "\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                BREAK +
                "\n");
    }

    /**
     * Goodbye Message: bid farewell to the user
     */
    public void farewell() {
        System.out.println(BREAK +
                "\n" +
                "     Goodbye! Have a nice Day!\n" +
                BREAK +
                "\n");
    }

    /**
     * Error Message: remind the user
     */
    public void showLoadingError() {
        System.out.println(BREAK +
                "\n" +
                "     ☹ OOPS!!! I cannot load your file!" +
                "\n" +
                BREAK +
                "\n");
    }
}