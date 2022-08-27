package blob.commands;

import blob.common.Messages;

/**
 * The ByeCommand class represents the exiting command of the application.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.MESSAGE_GOODBYE_1, Messages.MESSAGE_GOODBYE_2);
    }
}
