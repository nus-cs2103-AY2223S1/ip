import java.util.Scanner;

/**
 * Manages the user interaction of Duke
 * @author Shaune Ang
 */
public class Ui {
    private static final String HLINE = "\t____________________________________________";
    private Scanner myScanner;

    /**
     * Ui constructor initiates scanner object
     */
    public Ui() {
        myScanner = new Scanner(System.in);
    }

    /**
     * Prints generic greet message
     */
    public static void greet() {
        System.out.println(HLINE);
        System.out.println("\tHello I'm DUKE!\n\tWhat can I do for you?");
        System.out.println(HLINE);
    }

    /**
     * Prints generic good bye message
     */
    public static void goodBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints horizontal line
     */
    public static void showLine() {
        System.out.println(HLINE);
    }

    /**
     * Reads and returns full command given by user
     * @return full command given by user
     */
    public String readCommand() {
        return myScanner.nextLine();
    }
}
