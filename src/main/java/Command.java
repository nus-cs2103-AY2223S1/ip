import duke.exceptions.DukeException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract String getCommand();
}
