public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList task, Ui ui, Storage storage);
}
