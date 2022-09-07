package duke;

public class ListCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    public ListCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.ui.showTasks(this.tasks);
    }
}
