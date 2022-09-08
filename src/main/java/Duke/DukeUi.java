package Duke;
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

    public String readCommand(String input) {
        String[] command = input.split(" ");
        String userCommand = command[0];
        String userAction = "";
        for (int i = 0; i < command.length; i++) {
            if (i != 0) {
                userAction = userAction + command[i] + " ";
            }
        }
        userAction.strip();
        return userCommand + "_______________" + userAction;
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
