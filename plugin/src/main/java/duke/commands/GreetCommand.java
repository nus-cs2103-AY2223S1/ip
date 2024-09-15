
package duke.commands;

import duke.enums.Messages;

/**
 * Greets the user
 */
public class GreetCommand extends DisplayCommand {

    public GreetCommand() {

    }

    /**
     * Prints the greeting message
     *
     * @return wrapped message
     */
    @Override
    public String execute() {
        return wrapWithoutLines(Messages.GREET.toString(), Messages.LOGO.toString());
    }
}
