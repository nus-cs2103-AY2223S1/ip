
package duke.commands;

import java.io.IOException;

import duke.enums.Messages;
import duke.exceptions.DukeException;

/**
 * This is the base command class that does not need to validate anything and
 * only displays something to console
 */
public class DisplayCommand implements Command {

    public DisplayCommand() {

    }
    /**
     * Prints line separations before and after the messages
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
     * @throws DukeException when there is a DukeException
     * @throws IOException when there is an IOError
     */
    public void execute() throws DukeException, IOException {
        wrapWithLines("Testing...1,2,3...");
    }
}
