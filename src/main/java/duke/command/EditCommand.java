package duke.command;

import duke.exception.DukeException;
import duke.exception.OutOfRangeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 *  Created when user inputs "edit"
 */
public class EditCommand extends Command {
    private String taskDetails;
    private int num;

    /**
     * Constructor for an edit command.
     *
     * @param num The number of the task to be edited.
     * @param taskDetails The new description of the task.
     */
    public EditCommand(int num, String taskDetails) {
        this.taskDetails = taskDetails;
        this.num = num;
    }

    /**
     * Edits the description of the specified task in the task list
     *
     * @param storage Storage of the current duke.Duke program.
     * @param ui UI of the current duke.Duke program.
     * @param taskList Tasklist of the current duke.Duke program.
     * @return String output of the new task.
     * @throws DukeException If number provided is out of range of the size of the task list.
     */
    @Override
    public String execute(Storage storage, UI ui, TaskList taskList) throws DukeException {
        if (!taskList.isInRange(num - 1)) {
            throw new OutOfRangeException();
        }
        taskList.getTask(num - 1).setDescription(taskDetails);
        storage.save(taskList.list());
        return "I have edited the description of the task. It is now:\n"
            + taskList.getTask(num - 1).toString();
    }
}
