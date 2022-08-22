import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showToUser(String... message) {
        for (String msg : message) {
            System.out.print(msg);
        }
    }

    public void showToUserWithIndent(String... message) {
        for (String msg : message) {
            System.out.printf("|  %s%n", msg);
        }
    }

    public String getUserCommand() {
        showToUser("\njduke> ");
        String input = in.nextLine().trim();
        return input;
    }

    public void showGreeting() {
        showToUserWithIndent("Welcome to JDuke -- Version 1.0", "What can I do for you?");
    }

    public void showGoodbye() {
        showToUserWithIndent("Goodbye");
    }

    public void showErrorMessage(String error) {
        showToUserWithIndent("Error:", error);
    }
}
