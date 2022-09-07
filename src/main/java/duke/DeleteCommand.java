package duke;

public class DeleteCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    public DeleteCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        try {
            this.tasks.deleteTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showRemoveTaskSuccess(Parser.parseTaskNumber(userResponse), this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
