package wanya;

import java.util.Scanner;

/**
 * Represents user interface to deal with interactions from user.
 */
public class Ui {
    private final Scanner SC = new Scanner(System.in);
    private boolean isActive = true;
    private final String DIVIDER = "\n";

    /**
     * Displays the greeting message.
     */
    public void greet() {
        String startMsg = "Hello!!! My name is Wanya! \nWWaku WWaku! \nHow can I help you?";
        System.out.println(startMsg + DIVIDER);
    }

    /**
     * Displays the closing message and close the bot.
     */
    public void exit() {
        String closeMsg = "Yayyy Wanya gets to slack and watch shows now. Bye bye! :)";
        System.out.println(closeMsg + DIVIDER);
        isActive = false;
        SC.close();
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Reads the text entered by user.
     *
     * @return text entered by user.
     */
    public String getUserCommand() {
        String commandInput = SC.nextLine();
        return commandInput;
    }

    /**
     * Displays the error message to user.
     *
     * @param e Exception thrown.
     */
    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage() + DIVIDER);
    }

    /**
     * Displays the message for user to enter valid date time format.
     *
     * @param taskType the initial of the task.
     */
    public void showDateTimeFormat(String taskType) {
        String preposition = taskType.equals("E") ? "/at" : "/by";
        System.out.println("Please enter a valid date behind " + preposition + " with the format " +
                        "\"yyyy-mm-dd HH:mm\" where time is optional.\n " +
                         "If time is provided, leave it in 24 hours format." + DIVIDER);
    }

    /**
     * Displays that bot is starting up.
     */
    public void showLoading() {
        System.out.println("....Loa....Loading....Please wait...." + DIVIDER);
    }
}
