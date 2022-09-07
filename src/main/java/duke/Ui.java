package duke;

import java.util.Scanner;

/**
 * Represent the responses that Duke says
 */
public class Ui {
    private final Scanner sc;

    /**
     * Creates the Ui and initialises Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the String introduction message.
     */
    public static String printIntro() {
        return "I am the Duke of Edinburgh, what can I do for you peasant";
    }

    /**
     * Returns the String exit message of Duke program.
     *
     * @return Exit message.
     */
    public static String printExit() {
        return "Bye, I hope i never see you again you beggar";
    }
}
