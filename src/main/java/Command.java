abstract public class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws UncException;

    public abstract boolean isExit();
}
