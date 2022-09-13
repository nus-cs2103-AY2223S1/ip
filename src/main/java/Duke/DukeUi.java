package Duke;
import java.util.Arrays;
import java.util.Scanner;

/**
* DukeUi is the user interface of Duke to return responses according to user inputs
*
* @author Linus Chui
*/
public class DukeUi {
    private static final String LINE = "____________________________________________________________";
    private static final String START_MESSAGE = " Hello! I'm Duke\n" + " What can I do for you?";
    private static final String END_MESSAGE = " Bye. Hope to see you again soon!";
    static final String INVALID_COMMAND = " is not a valid command !! " +
            "valid commands are list, mark (number), unmark (number), todo (task), deadline (task) /by yyyy/mm/dd," +
            "event (task) /at (time), delete (number), find (keyword)";
    public static final String INVALID_DESCRIPTION = "You need to provide a description for the command !";
    public static final String INVALID_INDEX = "The index provided needs to be a number!";
    public static final String INDEX_OUT_OF_RANGE = "I'm sorry, but the index you provided is out of range :-(";
    public static final String INVALID_EVENT = "The description/time of an event cannot be empty!";
    public static final String INVALID_DEADLINE = "The description/time of a deadline cannot be empty!";
    public static final String INVALID_DATE = "Deadline date must be of format yyyy-mm-dd";
    public static final String ADD_TASK_ERROR = "Error adding task, please try again";
    public static final String DELETE_TASK_ERROR = "Error deleting task, please try again";


    /**
     * Splits user input into a String array of length 2.
     *
     * @param input the user inpuut.
     * @return a String array consisting of the user input of maximum length 2.
     * where index 0 is the command and index 1 is the input.
     */
    public String[] readCommand(String input) {
        assert input != "" : "Please input a command!";
        String[] command = input.split(" ", 2);
        return command;
    }

    /**
     * Prints a message from Duke.
     *
     * @param message the message to be printed out.
     */
    public static String sendMessage(String message) {
        return message;
    }

    /**
     * Prints Duke's welcome message when program starts.
     */
    public static String welcomeMessage() {
        return START_MESSAGE;
    }

    /**
     * Prints a loading error if Duke is unable to find a .txt file to load
     * in the user's device and informs the user that a new file will be created
     * to save the user's tasks.
     */
    public String showLoadingError() {
        return "File not found, creating new file in current directory";
    }

    /**
     * Prints a line to mark the end of Duke's response.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the error message.
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints Duke's goodbye message before closing program.
     */
    public String endMessage() {
        return END_MESSAGE;
    }
}
