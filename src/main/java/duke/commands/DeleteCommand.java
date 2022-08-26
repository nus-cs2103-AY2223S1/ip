package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs delete a specified task from TaskList command.
 */
public class DeleteCommand implements Command {
    /** Index of task to be deleted */
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskList.isEmpty()) {
                ui.printDeleteOnEmptyList();
            } else {
                Task task = taskList.getTask(index);
                taskList.deleteTask(index);
                storage.saveToFile(taskList);
                ui.printDeleteTask(index, task);
            }
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
