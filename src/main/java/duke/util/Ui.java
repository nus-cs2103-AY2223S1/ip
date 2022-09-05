package duke.util;

/**
 * Class to deal with interactions with the user.
 *
 * @author Kavan
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

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
        System.out.println(HORIZONTAL_LINE + "\nHello! I'm Duke\nWhat can I do for you?\n" + HORIZONTAL_LINE);
    }
}
