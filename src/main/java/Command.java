public abstract class Command {
    public abstract void execute(TaskList tasklist, Storage storage, Ui ui) throws DukeException;

    public abstract boolean isExit();
}