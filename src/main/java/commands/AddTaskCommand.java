package commands;

import duke.Deadline;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddTaskCommand extends Command {

    private TaskList tasks;
    private Task task;
    private Ui ui;

    public AddTaskCommand(TaskList tasks, Task task, Ui ui) {
        this.tasks = tasks;
        this.task = task;
        this.ui = ui;
    }

    public void execute() {
        tasks.add(task);
        ui.showAddedTask(tasks);
    }
}