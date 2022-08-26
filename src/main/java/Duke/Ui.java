package Duke;
import java.util.Scanner;

/**
 * Provides the user interface for Duke.
 * User will interact with the Duke programme thought this class. Provides
 * formatting methods for input and output.
 */
public class Ui {

    /**
     * Assets for the UI.
     */
    private static final Scanner INPUT_READER = new Scanner(System.in);

    /**
     * Reads the user input.
     * @return The user input.
     */
    public String readCommand() {
        System.out.printf("You: ");
        String input = INPUT_READER.nextLine();
        showHorizontalLineShort();
        return input;
    }

    /** 
     * Shows the horizontal line that is to be placed after each user input.
     */
    public void showHorizontalLineShort() {
        System.out.println("----------------");
    }

    /** 
     * Shows the long horizontal line that is to be placed after the
     * reponse of Duke.
     */
    public void showHorizontalLineLong() {
        System.out.println("---------------------------------------------");
    }

    /**
     * Shows the header for Duke's response.
     */
    public void showResponseHeader() {
        System.out.println("Duke: ");
    }

    /**
     * Formats the reponse with indentation.
     * @param response The response to be formatted.
     */
    public void showResponse(String response) {
        showResponseHeader();
        System.out.println(response.replace("\n", "\n    "));
    }

    /**
     * Indents the given string.
     * @param response The text to be formatted.
     * @return The indented response.
     */
    public String indentResponse(String response) {
        return String.format("    %s", response.replace("\n", "\n    "));
    }

    /**
     * Shows the error when Duke cannot read local files for writing and 
     * retrieving the tasks.
     */
    public void showLoadingError() {
        System.out.println("Uhoh! Duke is unable to access file storage.");
        System.out.println("Your list will not be saved locally.");
    }

    /**
     * Formats a general error for user ouput.
     * 
     * @param errorMsg The error message to be displayed.
     */
    public void showErr(String errorMsg) {
        System.out.println(indentResponse(String.format("[ERROR] %s", errorMsg)));
    }

    /**
     * Formats a general warning for user ouput.
     * 
     * @param errorMsg The warning message to be displayed.
     */
    public void showWarning(String errorMsg) {
        System.out.println(indentResponse(String.format("**[Warning] %s", errorMsg)));
    }

    /** Showss the welcome message for the user. */
    public void showWelcome() {
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello, welcome to Duke!");
    }

    /** Shows the exit message for the user. */
    public void showExit() {
        System.out.println("Goodbye, see you next time!");
    }

}
