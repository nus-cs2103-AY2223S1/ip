public abstract class Command {

    protected String content;
    protected TaskList tasks;

    public Command() {}

    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command(String content, TaskList tasks) {
        this.content = content;
        this.tasks = tasks;
    }

    public abstract String run() throws DukeException;

}
