package duke.commands;

import duke.enums.*;

public class GreetCommand extends DisplayCommand {

    public GreetCommand() {

    }

    /**
     * Prints the greeting message
     */
    @Override
    public void execute() {
        wrapWithLines(Messages.GREET.toString(), Messages.LOGO.toString());
    }
}