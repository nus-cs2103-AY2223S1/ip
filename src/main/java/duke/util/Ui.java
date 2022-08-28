package duke.util;

import java.util.Scanner;

/**
 * Class to deal with interactions with the user.
 *
 * @author Kavan
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "  ____________________________________________________________";
    private Scanner commands;

    /**
     * Constructor for Ui Class.
     */
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE + "\n  Hello! I'm Duke\n  What can I do for you?\n" + HORIZONTAL_LINE);
        this.commands = new Scanner(System.in);
    }

    /**
     * Returns the user input as a String.
     *
     * @return User Input.
     */
    public String getUserCommand() {
        return commands.nextLine();
    }
}

