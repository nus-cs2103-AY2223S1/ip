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

    public String readCommand() {
        System.out.printf("You: ");
        String input = INPUT_READER.nextLine();
        showHorizontalLineShort();
        return input;
    }

    public void showHorizontalLineShort() {
        System.out.println("----------------");
    }

    public void showHorizontalLineLong() {
        System.out.println("---------------------------------------------");
    }

    public void showResponseHeader() {
        System.out.println("\nDuke: ");
    }

    public void showResponse(String response) {
        showResponseHeader();
        System.out.println(response.replace("\n", "\n    "));
    }

    public String indentResponse(String response) {
        return String.format("    %s", response.replace("\n", "\n    "));
    }

    public void showLoadingError() {
        System.out.println("Uhoh! Duke is unable to access file storage.");
        System.out.
        println("Your list will not be saved locally.");
    }

    public void showErr(String errorMsg) {
        System.out.println(indentResponse(String.format("[ERROR] %s", errorMsg)));
    }

    public void showWarning(String errorMsg) {
        System.out.println(indentResponse(String.format("**[Warning] %s", errorMsg)));
    }

    public void showWelcome() {
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello, welcome to Duke!");
    }

    public void showExit() {
        System.out.println("Goodbye, see you next time!");
    }

}
