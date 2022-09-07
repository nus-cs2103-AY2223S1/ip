package duke;

public class FindCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    public FindCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        return this.ui.showMatchingTasks(Parser.parseSearchInput(userResponse), this.tasks);
    }
}
