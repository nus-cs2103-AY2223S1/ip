public abstract class Command {
    public boolean stillRunning = true;
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;
}
