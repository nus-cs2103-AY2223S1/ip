public abstract class Command {
    public abstract boolean isBye();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
