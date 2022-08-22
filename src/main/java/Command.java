public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);

    public boolean isExit() {
        return false;
    }
}
