package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    public static final String ADD_TODO = "todo";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_EVENT = "event";

    public static final String MESSAGE_SUCCESS = "Got it! I've added this task: \n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }
    public Task getTask() {
        return taskToAdd;
    }

    @Override
    public String execute(List tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(taskToAdd);
            storage.save();
            return ui.showToUser(String.format(MESSAGE_SUCCESS, taskToAdd, tasks.numberOfTasks()));
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

}
