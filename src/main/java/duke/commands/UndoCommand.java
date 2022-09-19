package duke.commands;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * A command that undoes the previously executed command.
 */
public class UndoCommand extends Command {

    private Deque<Command> commandHistory;
    private Command prevCommand;

    /**
     * Constructs a new command to undo the previous command.
     * @param commandHistory Deque storing the history of executed commands.
     */
    public UndoCommand(Deque<Command> commandHistory) {
        this.commandHistory = commandHistory;
    }

    /**
     * Undoes the previous command.
     * @return Response message.
     */
    @Override
    public String execute() {
        try {
            prevCommand = commandHistory.pop();
            return prevCommand.undo();
        } catch (NoSuchElementException e) {
            return "Oops! There is nothing to undo.";
        }
    }

    /**
     * Does nothing.
     * @return An empty string.
     */
    @Override
    public String undo() {
        return "";
    }

}
