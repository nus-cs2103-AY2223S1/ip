abstract class Command {

    abstract void execute(TaskMaker tasks, Ui ui, Storage storage);

    boolean isExit() {
        return false;
    }
}
