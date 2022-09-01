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
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes tasks and prints the delete statement.
     *
     * @param tasks The list of tasks where the command is executed.
     * @param file The file to write into.
     * @param storage The storage where the file is located.
     * @return String that is related to the command line.
     * @throws IsaraException The exception that is thrown if one of the commands catch an error.
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
