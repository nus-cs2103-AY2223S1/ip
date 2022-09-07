package duke;

public class MarkCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    public MarkCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        try {
            this.tasks.markTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showMarkSuccess(Parser.parseTaskNumber(userResponse));
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
