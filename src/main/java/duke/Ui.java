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

    private boolean isOpen = true;

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
    }


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
        }
        return c;
    }

}
