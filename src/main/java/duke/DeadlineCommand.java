package duke;

public class DeadlineCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    public DeadlineCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        try {
            this.tasks.addTasks(Parser.parseDeadlineTask(userResponse));
            return this.ui.showAddTaskSuccess(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
