package duke;

/**
 * Represents a command used in the Duke program.
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
