public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean toClose() {
        return false;
    };

}
