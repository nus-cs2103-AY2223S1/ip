package duke;

import java.util.Scanner;

public class Ui {
    private final static String UNDERLINE = "_________________________________";

    /**
     * Outputs the greeting for Duke
     *
     */
    public static String intro() {
        String res = UNDERLINE + "\n" + "Hello! I'm Duke!"  + "\n" + "What can I do for you?" + "\n" + UNDERLINE;
        return res;
    }

    /**
     * Outputs the ending for Duke
     *
     */
    public static String end() {
        String byeMessage = "Bye! See you again soon";
        return UNDERLINE + "\n" + byeMessage  + "\n" + UNDERLINE;
    }

    /**
     * Outputs the error message for Duke
     *
     * @param error Error message string
     */
    public String showError(String error) {
        return UNDERLINE + "\n" + error + "\n" + UNDERLINE;
    }

    /**
     * Gets user input
     *
     * @return user input
     */
    public String getCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }


    /**
     * Outputs message for Duke
     *
     * @param message message from Duke
     */
    public String printMessage(String message) {
        return UNDERLINE + "\n" + message + "\n" + UNDERLINE;
    }
}
