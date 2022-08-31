package duke.ui;

import duke.parser.ParserDuke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents Ui that interacts with the user and facilitates conversation
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private static final String END_CMD = "bye";

    /**
     * Constructs a Ui object
     * @param in InputStream for user commands
     * @param out PrintStream for responses displayed
     */
    public Ui(InputStream in, PrintStream out) {

        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints Duke's self-introduction and helps to customise its personality.
     */
    public void introduceDuke() {
        String intro = "I once wandered these halls, centuries ago. I am Duke Aemon of Old.\n";
        String quote1 = "Indeed, my memory is long when I am but a ghost of a memory myself..." +
                "\nBut you are young blood. What brings you to these ancient halls?"
                +"\n***********************************************************************";

        System.out.println("Welcome, my friend!\n" + intro + quote1);
    }

    /**
     * Exits chat if user types "bye" and prints user input otherwise.
     */
    public static void echoAndExit() {
        String exitCmd = "bye";
        while (true) {
            Scanner readinput = new Scanner(System.in);
            String userMsg = readinput.nextLine();
            if (!userMsg.equals(exitCmd)) {
                System.out.println(userMsg + "\n" + "***********************************************************************\n");
            } else {
                System.out.println("Ah! And so we part here today.\n We may yet meet again...Farewell, my friend!");
                break;
            }
        }
    }


    /**
     * Prints a farewell message at the end of the conversation
     */

    public void bidFarewell() {

        System.out.println("Ah! And so we part here today.\n We may yet meet again...Farewell, my friend!");
        System.out.println("\n***********************************************************************");
    }


    /**
     * Asks user to repeat message using standard commands if it does not comply with standard input format
     * @param userMsg String representing user message
     */

    public void askForClarification(String userMsg) {

        System.out.print("Did you say..." + userMsg + "?\n");
        System.out.println("The shadow of my memory is long...State what you would ask clearly.");
        System.out.println("\n***********************************************************************");
    }


    /**
     * Reads user message and prints an appropriate response back
     */

    public void readAndRespond() {


        Scanner readInput = this.in;

        while (true) {
            String userMsg = readInput.nextLine();
            if (userMsg.equals(END_CMD)) {
                bidFarewell();
                break;
            } else {
                ParserDuke parseCmd = new ParserDuke(userMsg);
                parseCmd.parseCommand();
            }
        }
    }
}