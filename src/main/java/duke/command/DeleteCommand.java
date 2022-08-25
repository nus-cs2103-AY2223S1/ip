package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.processor.TaskList;
import duke.task.Task;

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
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes any task that has been inputted.
     *
     * @param tasks The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the duke.Duke bot, thrown when
     *     the user does not include a proper description of the task.
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        try {
            Ui ui = new Ui();
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            int amountOfTasksLeft = tasks.getNumberOfTasks();
            ui.delete(task, amountOfTasksLeft);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Looks like the task you're looking for does not exist :-(");
        }
    }
}
