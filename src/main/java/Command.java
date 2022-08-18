public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, Ui ui);

    public boolean isExit() {
        return false;
    }
}
