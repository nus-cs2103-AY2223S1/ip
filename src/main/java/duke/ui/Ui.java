package duke.ui;
import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 *
 * @author Bryan Ng Zi Hao
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a line.
     */
    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    /**
     * Greets the user when the bot starts.
     */
    public static void greet() {
        printLine();
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        System.out.println("\t" + " Hello! I'm Duke\n");
        System.out.println("\t" + " What can I do for you?");
        printLine();
    }

    /**
     * Wishes the user farewell.
     */
    public static void exit() {
        printLine();
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Reads in whatever input the user provides.
     *
     * @return String format of what the user wrote.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Reads in whatever input the user provides.
     *
     * @param message is formatted to include an indent.
     */
    public void formatMessage(String message) {
        System.out.println("\t " + message);
    }
}
