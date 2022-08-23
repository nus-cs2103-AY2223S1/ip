public abstract class Command {
    public Task task;
    public Command(Task task) {
        this.task = task;
    }
    public Command() {

    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
