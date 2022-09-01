package commands;

import tasks.TaskList;
import ui.Ui;

public class PrintTasksCommand extends Command {

    private Ui ui;
    private TaskList tasks;

    public PrintTasksCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    @Override
    public boolean execute() {
        ui.showTasks(tasks);
        return true;
    }
}
