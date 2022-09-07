package duke;

public class UnmarkCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    private String userResponse;

    public UnmarkCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        try {
            this.tasks.unmarkTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showUnmarkSuccess(Parser.parseTaskNumber(userResponse));
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
