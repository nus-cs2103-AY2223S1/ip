public abstract class Command {
    public abstract void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isTerminator();
}
