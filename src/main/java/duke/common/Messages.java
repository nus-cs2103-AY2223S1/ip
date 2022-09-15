package duke.common;

/**
 * Represents a class that stores all the standard messages used in the program.
 * Inspiration taken from referenced
 * <a href="https://github.com/se-edu/addressbook-level2/tree/master/src/seedu/addressbook/common">addressbook</a>
 * program.
 *
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class Messages {
    /** Start up outputs. */
    public static final String LOGO = " ___  ___  __ __ \n"
            + "| . \\| __>|  \\  \\\n"
            + "|   /| _> |     |\n"
            + "|_\\_\\|___>|_|_|_|\n";
    public static final String WELCOME = "Konnichiwa! I'm Rem desu! :>\n" + "What can I do for you today?\n";
    public static final String GOODBYE = "See you soon! <3";

    /** Command error outputs */
    public static final String UNKNOWN_COMMAND = "Sorry, I don't understand. T^T\n"
            + "Please start your command with list, mark, unmark, todo, deadline, event, find or bye. :')\n";
    public static final String INVALID_TASK_NUMBER = "Please enter a valid task number to %s! T^T";
    public static final String EMPTY_TASK_ERROR = "There's nothing in your list to %s! T^T";
}
