package commands;

import enums.*;
import exceptions.DukeException;

/**
 * This is the base command class that does not need to validate anything and
 * only displays something to console
 */
public class Display implements AbstractCommand {

    public Display() {

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
     */
    public void execute() throws DukeException {
        wrapWithLines("Testing...1,2,3...");
    }
}