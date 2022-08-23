package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

class CreateTaskCommand extends Command {
    Task task;

    CreateTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.store(task);
        ui.showMessage(String.format("Got it. I've added this task:\n\t%s\n", task));
        storage.save(taskList);
    }
}