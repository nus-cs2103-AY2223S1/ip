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
    /** Rem bot logo */
    public static final String LOGO = " ___  ___  __ __ \n"
            + "| . \\| __>|  \\  \\\n"
            + "|   /| _> |     |\n"
            + "|_\\_\\|___>|_|_|_|\n";

    /** Output filler */
    public static final String SPACER = "----------------------------------------------------";

    /** Welcome message */
    public static final String WELCOME = "Konnichiwa! I'm Rem desu! :>\n"
            + "What can I do for you today?\n";

    /** Goodbye message */
    public static final String GOODBYE = "See you soon! <3";

    /** Unknown command message */
    public static final String UNKNOWN_COMMAND = "Sorry, I don't understand. T^T\n"
            + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n";
}
