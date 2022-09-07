package duke;

public class EventCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    public EventCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        this.tasks.addTasks(Parser.parseEventTask(userResponse));
        return this.ui.showAddTaskSuccess(this.tasks);
    }
}
