package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;

/**
 * Handles the case of marking a Task as done.
 */

public class TaskDoneCommand extends Command {

    private int idx;

    /**
     * Constructor for TaskDoneCommand.
     *
     * @param idx Index of task in task list to be marked as done.
     */
    public TaskDoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to mark task as done.
     *
     * @param taskList List of tasks.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws ZeusException If user input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        if (this.idx < 0 || this.idx >= taskList.getSize()) {
            throw new ZeusException("☹ OOPS!!! Invalid index entered");
        }
        // Before each command is executed, add prev version to the stack
        taskList.saveCurrentTaskListVersion();

        taskList.setTaskDone(this.idx);
        ui.addMessageToResponse("Excellent! I've marked this task as done:\n");
        ui.addMessageToResponse("\t   " + taskList.getTask(this.idx));

        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
