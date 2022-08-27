public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws UwuException;
    public abstract boolean isExit();
}
