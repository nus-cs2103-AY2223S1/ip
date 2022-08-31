package isara.command;

import java.io.File;

import isara.Ui;
import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;
import isara.task.Task;

/**
 * Class that represents the command 'mark'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class MarkCommand extends Command {
    /** The index referring to the task in the taskList. */
    private int taskIndex;

    /**
     * Constructs an instance of 'MarkCommand'.
     *
     * @param taskIndex The index referring to the task in the taskList.
     */
    public MarkCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks that the user has inputted.
     * @throws IsaraException Exceptions exclusive to the duke.Duke bot, thrown when
     *     the user does not input a number, or inputs invalid characters after the
     *     'mark' command.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) throws IsaraException {
        try {
            Ui ui = new Ui();
            Task task = tasks.getTask(taskIndex);
            task.markAsDone();
            storage.writeAndSaveToFile(file, tasks);
            return ui.mark(task);
        } catch (Exception e) {
            throw new IsaraException("☹ OOPS!!! The number you are talking about does not exist. "
                    + "\nPerhaps it is not a number at all? Please check again!");
        }
    }
}
