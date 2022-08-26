package Duke;

import java.util.Scanner;

public class Ui {
    private final static String UNDERLINE = "_________________________________";

    /**
     * Outputs the greeting for Duke
     *
     */
    public static void intro() {
        System.out.println(UNDERLINE);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(UNDERLINE);
    }

    /**
     * Outputs the ending for Duke
     *
     */
    public static void end() {
        String byeMessage = "Bye! See you again soon";
        System.out.println(UNDERLINE + "\n" + byeMessage  + "\n" + UNDERLINE);
    }

    /**
     * Outputs the error message for Duke
     *
     * @param error Error message string
     */
    public void showError(String error) {
        System.out.println(UNDERLINE + "\n" + error + "\n" + UNDERLINE);
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
    public void printMessage(String message){
        System.out.println(UNDERLINE + "\n" + message + "\n" + UNDERLINE);
    }
}
