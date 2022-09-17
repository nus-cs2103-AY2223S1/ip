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
    private static final String BREAK_LINE = "\n";
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
                reply = userMsg + BREAK_LINE;
            } else {
                reply = "Ah! And so we part here today." +
                        "\n We may yet meet again...Farewell, my friend!";
            }
            System.out.println(reply);
            return reply;
        }
    }

    /**
     * Prints Duke's self-introduction and helps to customise its personality.
     */
    public String introduceDuke() {
        String intro = "I once wandered these halls, centuries ago. I am Duke Aemon of Old.\n";
        String quote1 = "Indeed, my memory is long when I am but a ghost of a memory myself..." +
                "\nBut you are young blood. What brings you to these ancient halls?"
                + "\n***********************************************************************";

        String reply = "Welcome, my friend!\n" + intro + quote1;
        System.out.println(reply);
        return reply;
    }

    /**
     * Prints a farewell message at the end of the conversation
     */

    public String bidFarewell() {

        String reply = "Ah! And so we part here today."
                + "\n We may yet meet again...Farewell, my friend!"
                + BREAK_LINE;
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
        String response = "The shadow of my memory is long...State what you would ask clearly.";
        String reply = question + response + BREAK_LINE;
        System.out.println(reply);
        return reply;
    }


    /**
     * Reads user message and prints an appropriate response back
     */

    public String readAndRespond(String userMsg) {

        while (true) {
            assert userMsg != null;
            if (userMsg.equals(END_CMD)) {
                return bidFarewell();
            } else {
                ParserDuke parseCmd = new ParserDuke(userMsg);
                return parseCmd.parseCommand();
            }
        }
    }
}