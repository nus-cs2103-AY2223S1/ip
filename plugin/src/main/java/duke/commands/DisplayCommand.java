package duke.commands;

import java.io.IOException;

import duke.enums.*;
import duke.exceptions.DukeException;

/**
 * This is the base command class that does not need to validate anything and
 * only displays something to console
 */
public class DisplayCommand implements Command {

    public DisplayCommand() {

    }

    /**
     * This validates the semantics of the commmand
     * Commands with no inputs are vacuously true
     * 
     * @return If the input parameters are valid
     */
    public boolean validate() {
        return true;
    }

    /**
     * Prints line separations before and after the messages
     * 
     * @param msg The intended message to be printed
     */
    public void wrapWithLines(String... msg) {
        System.out.println(Messages.LINE_SEPARATION);
        for (String i : msg) {
            System.out.println(i);
        }
        System.out.println(Messages.LINE_SEPARATION);
    }

    /**
     * Prints a message
     * 
     * @throws DukeException
     * @throws IOException
     */
    public void execute() throws DukeException, IOException {
        wrapWithLines("Testing...1,2,3...");
    }
}