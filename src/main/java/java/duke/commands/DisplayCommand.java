
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
     * @return the wrapped message
     */
    public String wrapWithLines(String... msg) {
        String output = Messages.LINE_SEPARATION.toString();
        for (String i : msg) {
            output += "\n";
            output += i;
        }
        output += Messages.LINE_SEPARATION.toString();
        return output;
    }

    /**
     * Wraps message without lines
     * @param msg to be wrapped
     * @return joined messages
     */
    public String wrapWithoutLines(String ...msg) {
        String output = "";
        for (String i : msg) {
            output += "\n";
            output += i;
        }
        return output;
    }
    /**
     * Prints a message
     *
     * @return wrapped message
     * @throws DukeException when there is a DukeException
     * @throws IOException   when there is an IOError
     */
    public String execute() throws DukeException, IOException {
        return wrapWithoutLines("Testing...1,2,3...");
    }
}
