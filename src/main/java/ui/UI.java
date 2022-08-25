package ui;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class UI {
    private String currentInput;
    /**
     * Greets user.
     */
    public static void greetUser() {
        String logo = "_______     _\n"
                + "|  ___|    | |\n"
                + "|  |_  ____| |_____ ____  _  __\n"
                + "|   _|/ _  \\ | ___|/  _ \\| |/  \\\n"
                + "|  | | |_| | | |___| |_| |  / \\ |\n"
                + "|__|  \\__|_|_|____|\\____/|_|  |_|\n";
        System.out.println("Hello from");
        System.out.println(logo);
        // prompt user
        System.out.println("What would you like to do next?");
        System.out.print(">> ");
    }

    /**
     * Reads command entered
     * @return
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        currentInput = in.nextLine();
        return currentInput;
    }

    /**
     * Prints user prompt symbol >>.
     */
    public void showLine() {
        System.out.print(">>");
    }

    /**
     * Prints errorMsg,
     * usually DukeException.getMessage().
     * @param errorMsg Error message to print.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints when storage could not be synced.
     */
    public void showLoadingError() {
        System.out.println("error loading");
    }

    /**
     * Prints a general message/
     * @param msg Message to be included.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints when user terminates program
     * with bye command.
     */
    public void showExitMessage() {
        System.out.print("Thank you for swinging by :)");
    }

    /**
     * Prints when user inputs
     * help command.
     */
    public void showHelpMessage() {
        System.out.println("Hi, it seems you are having trouble using Falcon, "
                + "\ntry these command: "
                + "\ntodo\nlist\nevent\ndeadline\nmark\nunmark\nlongdesc\nistoday\nfind");
    }

    /**
     * Returns line user has entered at System.in.
     * @return User input line.
     */
    public String getCurrentInput() {
        return currentInput;
    }
}
