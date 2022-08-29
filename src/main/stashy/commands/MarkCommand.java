package main.stashy.commands;

import main.stashy.data.exception.StashyException;
import main.stashy.data.task.TaskList;
import main.stashy.storage.Storage;
import main.stashy.ui.Ui;

public class MarkCommand extends Command {
    public static final String KEYWORD = "mark";
    private int taskId;

    /**
     * Constructor method.
     *
     * @param taskId Task ID from the task list
     */
    public MarkCommand(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks a particular task from the given task list as done.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (1 <= this.taskId && this.taskId <= tasks.size()) {
            ui.showIndented("LFG, marking this task as done!");
            tasks.get(this.taskId - 1).markAsDone();
            ui.showIndented("  " + tasks.get(this.taskId - 1));
        } else {
            throw new StashyException("Invalid task ID: " + this.taskId);
        }
    }
}
