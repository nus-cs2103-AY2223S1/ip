package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

public class DeleteCommand extends Command {
    public static final String KEYWORD = "delete";
    private int taskId;

    /**
     * Constructor method.
     *
     * @param taskId Task ID from the task list
     */
    public DeleteCommand(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes a particular task from the given task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (1 <= this.taskId && this.taskId <= tasks.size()) {
            ui.showIndented("Task has been removed!\n  " + tasks.get(this.taskId - 1));
            tasks.remove(this.taskId - 1);
            ui.showIndented("You have " + tasks.size() + " task(s) in the list.");
        } else {
            throw new StashyException("Invalid task ID: " + this.taskId);
        }
    }
}
