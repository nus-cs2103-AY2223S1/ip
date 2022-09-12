package duke.command;

import duke.Responses;

/**
 * Concrete class for the BYE command.
 */
public class ByeCommand extends Command {
    @Override
    public String execute() {
        return Responses.BYE_MESSAGE;
    }
}
