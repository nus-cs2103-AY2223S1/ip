package blob.commands;

import blob.common.Messages;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye");
    }

    public CommandResult execute() {
        return new CommandResult(Messages.MESSAGE_GOODBYE_1, Messages.MESSAGE_GOODBYE_2);
    }
}
