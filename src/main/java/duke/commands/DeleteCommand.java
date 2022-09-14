package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class performs delete a specified task from TaskList command.
 */
public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    private int index;

    /**
     * Constructor for class DeleteCommand.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            return Ui.formatNoTaskToDeleteMessage();
        } else {
            Task task = taskList.getTask(index);
            taskList.removeTask(index);
            storage.saveToFile(taskList);
            return Ui.formatDeleteTaskMessage(index, task);
        }
    }
}
