public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage);

    public abstract boolean isExit();
}
