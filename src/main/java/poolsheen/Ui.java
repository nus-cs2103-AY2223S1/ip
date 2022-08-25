package poolsheen;

import java.util.ArrayList;
import java.util.Scanner;

import poolsheen.task.Task;

/**
 * Class that deals with interactions with the user.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */
public class Ui {
    public static final String BEGIN_SPACE = "      ";

    private static final String LOGO = "\n" + "POOLSHEEN";

    private static final String WELCOME_MESSAGE = "Hello from" + LOGO + "\n" +
            "Type something in for Poolsheen to respond to you:";

    private static final String LAST_REPLY = "meow *_*";

    private static final String HORIZONTAL_LINE = "---------------";

    private static final String GOODBYE_MESSAGE = "MeoAww... See you next time :(\nTHE POOLSHEEN PROGRAM HAS STOPPED RUNNING";

    private Scanner s;

    public Ui() {
        s = new Scanner(System.in);
        System.out.println("Poolsheen UI has loaded");
    }

    public static void showLoadingError() {
        System.out.println("Poolsheen has encountered a loading error");
    }

    /**
     * Prints a formatted message by Poolsheen.
     * @param message The message to be printed.
     */
    public void say(String message) {
        System.out.println(HORIZONTAL_LINE + "\n" +
                newLine(message) +
                newLine(LAST_REPLY) + HORIZONTAL_LINE);
    }

    /**
     * Prints a UI Error.
     * @param errMsg The string obtained from an error's getMessage function.
     */
    public void showError(String errMsg, String errorType) {
        System.out.println(HORIZONTAL_LINE + "\n" +
                newLine("The following UI error has occurred") +
                newLine("The error type is: " + errorType) +
                newLine(errMsg) +
                newLine("Please try again!") +
                newLine(LAST_REPLY) + HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks this Poolsheen remembers.
     */
    public void displayList(TaskList tl) {
        if (tl.isEmpty()) {
            this.say("Poolsheen thinks back... " +
                    "and remembers you said nothing :(");
        } else {
            String displayStr = "Poolsheen thinks back... " +
                    "and remembers you said:\n";
            this.say(displayStr + this.getListOfTasks(tl));
        }
    }

    /**
     * Returns a string of the list of tasks that Poolsheen remembers.
     */
    private String getListOfTasks(TaskList tl) {
        if (tl.isEmpty()) {
            return "";
        } else {
            String displayStr = "";
            for (int i = 0; i < tl.size(); i++) {
                int currPos = i + 1;
                Task t = tl.get(i);
                if (t != null) {
                    String line = currPos + ". " + t;
                    displayStr += this.BEGIN_SPACE + line;
                    if (currPos < tl.size()) {
                        displayStr += "\n";
                    }
                }
            }
            return displayStr;
        }
    }

    /**
     * Returns a new line for Poolsheen to say.
     * @param line The string for Poolsheen to say in the new line.
     */
    public String newLine(String line) {
        return BEGIN_SPACE + line + "\n";
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Reads the user's input.
     * @return A string of the user input.
     */
    public String readCommand() {
        return s.nextLine();
    }
}
