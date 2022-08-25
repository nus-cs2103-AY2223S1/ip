public abstract class Command {

    public abstract void execute(Ui ui, Storage storage, TaskList taskList);

    public Boolean isTerminated() {
        return this instanceof ByeCommand;
    }
}
