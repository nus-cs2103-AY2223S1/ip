package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.parser.ParserDuke;

/**
 * Represents Ui that interacts with the user and facilitates conversation
 */

public class Ui {

    private static final String END_CMD = "bye";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a Ui object with input and output streams.
     *
     * @param in  InputStream for user input
     * @param out PrintStream for output
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a farewell message at the end of the conversation
     */
    public String bidFarewell() {
        return "So ends our discourse.\n"
             + "I shall remember what you have entrusted to me.\n";
    }

    /**
     * Asks the user to clarify an unclear command
     */
    public String askForClarification() {
        return "Your words are unclear.\n"
             + "Speak plainly, or consult 'help'.\n";
    }

    /**
     * Returns an appropriate message for the user command
     *
     * @param userMsg String representing user command
     * @return String representing Duke's response
     */
    public String readAndRespond(String userMsg) {

        if (userMsg.equals(END_CMD)) {
            return bidFarewell();
        }

        try {
            ParserDuke parseCmd = new ParserDuke(userMsg);
            return parseCmd.parseCommand();
        } catch (Exception e) {
            return askForClarification() + e.getMessage();
        }
    }
}
