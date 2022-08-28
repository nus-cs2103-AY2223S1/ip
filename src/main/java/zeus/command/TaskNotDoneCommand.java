package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;

/**
 * Class that handles the case of marking a Task as not done.
 */

public class TaskNotDoneCommand extends Command {

    private int idx;

    /**
     * Constructor for TaskNotDoneCommand.
     *
     * @param idx
     */
    public TaskNotDoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to mark task as not done.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If user input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        if (this.idx < 0 || this.idx >= taskList.size()) {
            throw new ZeusException("☹ OOPS!!! Invalid index entered");
        }
        taskList.setTaskNotDone(this.idx);

        ui.addMessageToResponse("OK, I've marked this task as not done yet:\n");
        ui.addMessageToResponse("\t   " + taskList.getTask(this.idx));

        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
