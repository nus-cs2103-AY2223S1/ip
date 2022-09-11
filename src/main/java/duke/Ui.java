package duke;

import duke.command.Command;
import duke.parser.Parser;

import java.util.Scanner;

/**
 * A UI class to manage the user interactions of the app.
 */
public class Ui {
    private final static String LOGO = ".__ .  ..  ..___" + "\n" +
            "|  \\|  ||_/ [__ " + "\n" +
            "|__/|__||  \\[___" + "\n";

    private final static String NAME = "duke.Duke";
    private final static String LINE = "──────────────────────────────────────────";

    private boolean isOpen = true;
//    private Scanner in;

    public Ui() {
    }

    /**
     * Check if the UI is open.
     * @return a boolean on whether UI is open.
     */
    public boolean isOpen(){
        return this.isOpen;
    }

    /**
     * Starts the UI.
     */
    public void start() {
        this.isOpen = true;
        System.out.println(Ui.LOGO);
        this.printWithHorizontalRule("Hello! I'm " + Ui.NAME + "\n" + "What can I do for you?");
//        this.in = new Scanner(System.in);
    }

//    private String readInput() {
//        return in.nextLine();
//    }

    /**
     * Read the input from stdin, and converts it to a Command object.
     * If command object is an exit object, close the UI.
     * @return a Command object.
     */
    public Command readCommand(String input) {
        Parser.ParsedInputArguments args = Parser.getInputArguments(input);
        Command c = Command.getCommand(args);

        if (c.isExit()) {
            this.isOpen = false;
//            this.in.close();
        }
        return c;
    }

    /**
     * Print the given string with a horizontal rule before and after.
     * @param s string to be printed.
     */
    public void printWithHorizontalRule(String s) {
        printHorizontalRule();
        System.out.println(s);
        printHorizontalRule();
        System.out.println("");
    }

    private void printHorizontalRule() {
        System.out.println(LINE);
    }
}
