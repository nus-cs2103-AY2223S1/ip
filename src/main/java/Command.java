public abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    abstract boolean isExit();
}
