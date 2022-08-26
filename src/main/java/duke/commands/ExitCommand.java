
package duke.commands;

import duke.enums.Messages;

/**
 * Exits from main
 */
public class ExitCommand extends DisplayCommand {
    public ExitCommand() {

    }

    /**
     * Prints the exit message
     */
    @Override
    public void execute() {
        wrapWithLines(Messages.EXIT.toString());
    }
}
