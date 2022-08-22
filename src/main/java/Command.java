public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage);
    public abstract boolean isExit();
}
