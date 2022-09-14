package poolsheen;

import java.util.Scanner;

import poolsheen.task.Task;

/**
 * Represents everything that involves interactions with the user.
 */
public class Ui {
    /** The indentation for all printed Poolsheen lines. */
    public static final String BEGIN_SPACE = "      ";

    /** The message printed last whenever Poolsheen replys. */
    private static final String LAST_REPLY = "meow *_*";

    /** A horiontal line to distinguish each interaction the user has with Poolsheen. */
    private static final String HORIZONTAL_LINE = "--------------------------------------------------";

    /** The message first printed when the Poolsheen program runs. */
    private static final String WELCOME_MESSAGE = HORIZONTAL_LINE + "\n"
            + "hello! welcome back!\n Type something in for me to do :D\n"
            + HORIZONTAL_LINE;

    /** The message last printed when the Poolsheen program ends. */
    private static final String GOODBYE_MESSAGE = "MeoAww... :(\n"
            + "THE POOLSHEEN PROGRAM HAS ALREADY STOPPED RUNNING";

    /** The Scanner object which the Poolsheen program uses to interact with the user. */
    private Scanner s;

    /**
     * A public constructor to initialise a Ui object.
     */
    public Ui() {
        s = new Scanner(System.in);
        System.out.println("Poolsheen UI has loaded");
    }

    /**
     * Prints a message whenever Poolsheen encounters a loading error.
     */
    public static void showLoadingError() {
        System.out.println("Poolsheen has encountered a loading error");
    }

    /**
     * Returns a formatted message by Poolsheen.
     *
     * @param message The message to be printed.
     * @return The string to be used in the GUI.
     */
    public String say(String message) {
        return HORIZONTAL_LINE + "\n"
                + newLine(message)
                + newLine(LAST_REPLY) + HORIZONTAL_LINE;
    }

    /**
     * Returns a formatted string of the list of tasks this Poolsheen remembers.
     */
    public String displayList(TaskList tl) {
        if (tl.isEmpty()) {
            return this.say("Poolsheen thinks back... " + "and remembers you said nothing :(");
        } else {
            String displayStr = "Poolsheen thinks back... "
                    + "and remembers you said:\n";
            return this.say(displayStr + this.getListOfTasks(tl));
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
            for (int i = 0; i < tl.getSize(); i++) {
                int currPos = i + 1;
                Task t = tl.get(i);
                if (t != null) {
                    String line = currPos + ". " + t;
                    displayStr += BEGIN_SPACE + line;
                    if (currPos < tl.getSize()) {
                        displayStr += "\n";
                    }
                }
            }
            return displayStr;
        }
    }

    /**
     * Returns a new line for Poolsheen to say.
     *
     * @param line The string for Poolsheen to say in the new line.
     */
    public String newLine(String line) {
        return line + "\n";
    }

    /**
     * Prints the welcome message.
     */
    public static String getWelcome() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints the goodbye message.
     */
    public static String getGoodbye() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Reads the user's input.
     *
     * @return A string of the user input.
     */
    public String readCommand() {
        return s.nextLine();
    }
}
