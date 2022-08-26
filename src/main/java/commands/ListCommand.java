package commands;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    private TaskList tasks;
    private Ui ui;
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() {
        this.ui.showTaskList(this.tasks);
    }
}
