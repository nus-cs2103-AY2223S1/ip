package duke;
import java.util.Scanner;

/**
 * Class to encapsulate a User Interface.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads next input from user.
     *
     * @return The input from the user in String format.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a line to separate commands.
     */
    public void printBlankLine() {
        System.out.println("-------------------------------------------------");
    }

    /**
     * Prints welcome image to user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printString(String s) {
        System.out.println(s);
    }

}


