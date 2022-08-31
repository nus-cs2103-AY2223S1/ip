package isara.command;

import java.io.File;

import isara.Ui;
import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;
import isara.task.Task;

/**
 * Class to represent the command 'delete'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class DeleteCommand extends Command {
    /** The index referring to the task in the taskList. */
    private int taskIndex;

    /**
     * Constructs an instance of 'DeleteCommand'.
     *
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes any task that has been inputted.
     *
     * @param tasks The list of tasks that the user has inputted.
     * @throws IsaraException Exceptions exclusive to the duke.Duke bot, thrown when
     *     the user does not include a proper description of the task.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) throws IsaraException {
        try {
            Ui ui = new Ui();
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            int amountOfTasksLeft = tasks.getNumberOfTasks();
            storage.writeAndSaveToFile(file, tasks);
            return ui.delete(task, amountOfTasksLeft);
        } catch (Exception e) {
            throw new IsaraException("☹ OOPS!!! Looks like the task you're looking for does not exist :-(");
        }
    }
}
