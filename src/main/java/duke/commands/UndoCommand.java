package duke.commands;

import java.util.Deque;
import java.util.NoSuchElementException;

public class UndoCommand extends Command {

    private Deque<Command> commandHistory;
    private Command prevCommand;

    public UndoCommand(Deque<Command> commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Override
    public String execute() {
        try {
            prevCommand = commandHistory.pop();
            return prevCommand.undo();
        } catch (NoSuchElementException e) {
            return "Oops! There is nothing to undo.";
        }
    }

    @Override
    public String undo() {
        return "";
    }

}
