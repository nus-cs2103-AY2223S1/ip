package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.processor.TaskList;
import duke.task.Task;

/**
 * Class to represent the command 'unmark'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class UnmarkCommand extends Command {
    /** The index referring to the task in the taskList. */
    private int taskIndex;

    /**
     * Constructs an instance of 'UnmarkCommand'.
     *
     * @param taskIndex The index referring to the task in the taskList.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks a task as undone.
     *
     * @param tasks The list of tasks that the user has inputted.
     * @throws DukeException Exceptions exclusive to the duke.Duke bot, thrown when
     *     the user does not input a number, or inputs invalid characters after the
     *     'unmark' command.
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        try {
            Ui ui = new Ui();
            Task task = tasks.getTask(taskIndex);
            task.unmarkAsDone();
            ui.unmark(task);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The number you are talking about does not exist."
                    + "\nPerhaps it is not a number at all? Please check again!");
        }
    }

}
