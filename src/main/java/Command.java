public abstract class Command {

    protected boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FredException;

    public boolean isExit() {
        return isExit;
    }
}
