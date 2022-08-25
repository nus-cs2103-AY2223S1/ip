import java.util.Scanner;

// Manages the user interaction of Duke
public class Ui {
    static final String HLINE = "\t____________________________________________";
    Scanner myScanner;
    String  command = "";


    public Ui() {
        myScanner = new Scanner(System.in);
    }

    // Prints generic greet message
    public static void greet() {
        System.out.println(HLINE);
        System.out.println("\tHello I'm DUKE!\n\tWhat can I do for you?");
        System.out.println(HLINE);
    }

    // Prints generic goodbye message
    public static void goodBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    //Print dividing line
    public static void showLine() {
        System.out.println(HLINE);
    }

    /**
     * Reads and returns command given by user
     *
     * @return full command given by user
     */
    public String readCommand() {
        return myScanner.nextLine();
    }
}
