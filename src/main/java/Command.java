public abstract class Command {

    abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
