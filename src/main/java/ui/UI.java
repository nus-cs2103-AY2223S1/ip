package ui;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class UI {
    public String currentInput;
    /**
     * Greets user.
     */
    public static void greetUser() {
        String logo = "_______     _\n" +
                "|  ___|    | |\n" +
                "|  |_  ____| |_____ ____  _  __\n" +
                "|   _|/ _  \\ | ___|/  _ \\| |/  \\\n"+
                "|  | | |_| | | |___| |_| |  / \\ |\n" +
                "|__|  \\__|_|_|____|\\____/|_|  |_|\n";
        System.out.println("Hello from" );
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

    public void showLine() {
        System.out.println(">>");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showLoadingError() {
        System.out.println("error loading");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
