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
    public static final String initMessage =  "     Hello! I'm Duke.Duke\n" +
            "     I can store a to-do list for you!\n" +
            "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
            "     Start entering your tasks!\n";


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
    }

    /**
     * Returns the welcome message
     *
     * @return String of welcome message
     */
    public String getWelcomeMessage() {
        return "Hello from\n" + logo + "\n" + initMessage;
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
    public String showGoodbyeMessage() {
        return  "     Bye. Hope to see you again soon!\n";
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
    public String showEntry(String item) {
        return "    " + item;
    }

    /**
     * Prints delete message.
     *
     * @param deletable Task that was deleted
     * @param size      int size
     */
    public String showDeleteMessage(Task deletable, int size) {
        return String.format("     Noted. I've removed this task:\n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n", deletable, size);
    }

    /**
     * Prints invalid task message.
     */
    public String showInvalidTaskMessage() {
        return String.format("Invalid task! Please input a number!");
    }

    /**
     * prints Error message.
     */
    public String showErrorReadingMessage() {
        return String.format("Error reading from data!");
    }

    /**
     * Prints invalid index message.
     */
    public String showInvalidIndexMessage() {
        return "Please Include a valid index!";
    }

    /**
     * Prints Writing Error message.
     */
    public String showErrorWritingMessage() {
        return "Error writing to data!";
    }

    public String showInvalidFindFiledMessage() {
        return "Please include a word to search for!";
    }
}