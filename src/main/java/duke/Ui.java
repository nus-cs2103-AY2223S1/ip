package duke;

import java.util.Scanner;

/**
 * The user interface that the user sees.
 *
 * @author Derrick Khoo
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * The greeting from Duke that the user sees upon running a new instance of Duke
     */
    public void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    /**
     * The line that engulfs each message sent by Duke to the user.
     */
    public void line() {
        System.out.println("________________________________________");
    }

    /**
     * The method that reads the input from the user.
     *
     * @return the string representation of the input from the user
     */
    public String parseCommand() {
        return this.scanner.nextLine();
    }
}
