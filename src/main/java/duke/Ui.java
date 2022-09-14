package duke;

import java.util.Scanner;

public class Ui {
    private final static String UNDERLINE = "_______________________";

    /**
     * Outputs the greeting for Duke
     *
     */
    public static String intro() {
        String res = "Hello! I'm Duke!"  + "\n" + "What can I do for you?";
        return printMessage(res);
    }

    /**
     * Outputs the ending for Duke
     *
     */
    public static String end() {
        String byeMessage = "Bye! See you again soon";
        return printMessage(byeMessage);
    }

    /**
     * Outputs the error message for Duke
     *
     * @param error Error message string
     */
    public static String showError(String error) {
        return printMessage(error);
    }

    /**
     * Gets user input
     *
     * @return user input
     */
    public static String getCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }


    /**
     * Outputs message for Duke
     *
     * @param message message from Duke
     */
    public static String printMessage(String message) {
        return UNDERLINE + "\n" + message + "\n" + UNDERLINE;
    }
}
