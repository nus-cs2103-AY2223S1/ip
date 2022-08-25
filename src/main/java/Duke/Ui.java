package Duke;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Handles user input and outputs.
 */
public class Ui {

    /**
     * Duke Logo
     */
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Duke welcome message.
     */
    public static final String initMessage = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke.Duke\n" +
            "     I can store a to-do list for you!\n" +
            "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
            "     Start entering your tasks!\n" +
            "    ____________________________________________________________";


    /**
     * Scanner object to handle user input.
     */
    private final Scanner in;

    /**
     * Constructor for class.
     */
    public Ui() {
        this(System.in);
    }

    /**
     * Constructor for class.
     *
     * @param in  input stream
     */
    public Ui(InputStream in) {
        this.in = new Scanner(in);
        System.out.println("Hello from\n" + logo);
        System.out.println(initMessage);
    }

    /**
     * Gets next user input.
     *
     * @return string of user input
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
    }

    /**
     * Checks if there is a next input.
     *
     * @return boolean
     */
    public boolean hasInput() {
        return in.hasNext();
    }

    /**
     * Prints item
     *
     * @param item String
     */
    public void showEntry(String item) {
        System.out.println("    " + item);
    }

    /**
     * Prints delete message.
     *
     * @param deletable Task that was deleted
     * @param size      int size
     */
    public void showDeleteMessage(Task deletable, int size) {
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     Noted. I've removed this task:\n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________\n", deletable, size);
    }

    /**
     * Prints invalid task message.
     */
    public void showInvalidTaskMessage() {
        System.out.println("Invalid task! Please input a number!");
    }

    /**
     * prints Error message.
     */
    public void showErrorReadingMessage() {
        System.out.println("Error reading from data!");
    }

    /**
     * Prints invalid index message.
     */
    public void showInvalidIndexMessage() {
        System.out.println("Please Include a valid index!");
    }

    /**
     * Prints Writing Error message.
     */
    public void showErrorWritingMessage() {
        System.out.println("Error writing to data!");
    }
}