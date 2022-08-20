public abstract class Command {
    protected TaskList tasks;

    protected void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public abstract CommandResult execute() throws DukeException;
}
