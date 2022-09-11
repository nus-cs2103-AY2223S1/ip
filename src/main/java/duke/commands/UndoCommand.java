package duke.commands;

/**
 * Represents an undo command in the Duke application.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    private static final String USER_MESSAGE_FORMAT = "Undoing previous command!";

    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        return new CommandResult(USER_MESSAGE_FORMAT, CommandResult.Action.UNDO);
    }
}
