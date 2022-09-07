package duke;

public class TodoCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    public TodoCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        this.tasks.addTasks(Parser.parseTodoTask(userResponse));
        return this.ui.showAddTaskSuccess(this.tasks);
    }
}
