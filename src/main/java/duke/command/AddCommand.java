package duke.command;

import duke.*;
import duke.exception.*;
import duke.task.Task;
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAddTask(this.task, tasks);
    }

    @Override
    public String toString() {
        return "Add command of task: " + task.toString();
    }
}
