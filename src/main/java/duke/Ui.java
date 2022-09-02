package duke;

import duke.command.Command;
import duke.parser.Parser;

import java.util.Scanner;

/**
 * A UI class to manage the user interactions of the app.
 */
public class Ui {
    private static String Logo = ".__ .  ..  ..___" + "\n" +
            "|  \\|  ||_/ [__ " + "\n" +
            "|__/|__||  \\[___" + "\n";

    private static String Name = "duke.Duke";
    private static String Line = "──────────────────────────────────────────";

    private boolean open = true;
    private Scanner in;

    public Ui() {
    }

    /**
     * Check if the UI is open.
     * @return a boolean on whether UI is open.
     */
    public boolean isOpen(){
        return this.open;
    }

    /**
     * Starts the UI.
     */
    public void start() {
        this.open = true;
        System.out.println(Ui.Logo);
        this.printWithHorizontalRule("Hello! I'm " + Ui.Name + "\n" + "What can I do for you?");
        this.in = new Scanner(System.in);
    }

    private String readInput() {
        return in.nextLine();
    }

    /**
     * Read the input from stdin, and converts it to a Command object.
     * If command object is an exit object, close the UI.
     * @return a Command object.
     */
    public Command readCommand() {
        Parser.ParsedInputArguments args = Parser.getInputArguments(this.readInput());
        Command c = Command.getCommand(args);

        if (c.isExit()) {
            this.open = false;
            this.in.close();
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
        System.out.println(Line);
    }
}
