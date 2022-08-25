public abstract class Command {
    public static boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
