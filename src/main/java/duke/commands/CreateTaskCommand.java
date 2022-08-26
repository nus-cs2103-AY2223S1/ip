package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates and stores a task.
 */
class CreateTaskCommand extends Command {
    private final Task task;

    /**
     * Constructor for CreateTaskCommand.
     * @param task the task to be stored
     */
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
