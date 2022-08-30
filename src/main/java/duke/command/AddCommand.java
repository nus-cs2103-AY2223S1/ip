package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        storage.update(tasks);
        ui.showAddMessage(this.task, tasks.getNumTasks());
    };

    @Override
    public boolean isExit() {
        return false;
    };

}
