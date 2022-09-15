package duke;

import java.util.Scanner;

/**
 * Represents interactions with user through I/O.
 */
public class UI {
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the user-entered command.
     *
     * @return the user's command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a line divider to format the output.
     */
    public void showLine() {
        System.out.println("_______\n");
    }

    /**
     * Prints an error that occurred during execution.
     *
     * @param e Exception object thrown during execution.
     */
    public void showError(Exception e) {
        System.out.println("Oh no! Woof! Something went wrong: " + e.getMessage());
    }

    /**
     * Prints the opening message
     * when the user launches the program in the command line.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Teddy\n" + "What can I do for you?\n");
    }

    /**
     * Prints the given string.
     *
     * @param string String to be printed to the user.
     */
    public void print(String string) {
        System.out.print(string);
    }
}
