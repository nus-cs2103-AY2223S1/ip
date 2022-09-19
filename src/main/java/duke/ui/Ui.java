package duke.ui;

import duke.parser.ParserDuke;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents Ui that interacts with the user and facilitates conversation
 */
public class Ui {

    private static final String END_CMD = "bye";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a Ui object
     *
     * @param in  InputStream for user commands
     * @param out PrintStream for responses displayed
     */
    public Ui(InputStream in, PrintStream out) {

        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Exits chat if user types "bye" and prints user input otherwise.
     */
    public static String echoAndExit() {
        while (true) {
            Scanner input = new Scanner(System.in);
            String userMsg = input.nextLine();
            String reply;
            if (!userMsg.equals(END_CMD)) {
                reply = userMsg + "\n";
            } else {
                reply = "Ah! And so we part here today."
                        + "\n We may yet meet again...Farewell, my friend!";
            }
            System.out.println(reply);
            return reply;
        }
    }

    /**
     * Prints a farewell message at the end of the conversation
     */

    public String bidFarewell() {

        String reply = "Ah! And so we part here today."
                + "\n We may yet meet again...Farewell, my friend!\n";
        System.out.println(reply);
        return reply;
    }


    /**
     * Asks user to repeat message using standard commands if it does not comply with standard input format
     *
     * @param userMsg String representing user message
     */

    public String askForClarification(String userMsg) {

        String question = "Did you say..." + userMsg + "?\n";
        String response = "The shadow of my memory is long...State what you would ask clearly.\n";
        String reply = question + response;
        System.out.println(reply);
        return reply;
    }


    /**
     * Returns an appropriate message for the user command
     *
     * @param userMsg String representing user command
     * @return String representing Duke's response
     */

    public String readAndRespond(String userMsg) {

        while (true) {
            if (userMsg.equals(END_CMD)) {
                return bidFarewell();
            } else {
                try {
                    ParserDuke parseCmd = new ParserDuke(userMsg);
                    return parseCmd.parseCommand();
                } catch (Exception e) {
                    String response = askForClarification(userMsg);
                    String filler = "This is what my scribe tells me...\n ";
                    return response + filler + e.toString();
                }
            }
        }
    }
}
