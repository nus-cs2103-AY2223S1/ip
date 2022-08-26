
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
     *
     * @return wrapped message
     */
    @Override
    public String execute() {
        return wrapWithoutLines(Messages.EXIT.toString());
    }
}
