package duke.command;

import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;
import duke.task.Task;

/**
 * Represents a command that is used to unmark a task in the tasklist, save the task
 * and print out the task that was unmarked.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnmarkCommand
     *
     * @param index
     */
    public UnmarkCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Execute method that is used to unmark a task in a tasklist, save the task
     * and print out the task that was unmarked through tasklist, ui and storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws NumberFormatException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NullPointerException,
            IndexOutOfBoundsException, NumberFormatException {
            Task task = taskList.getTask(index - 1);
            task.setUndone();
            storage.saveTasks(taskList);
            ui.repeater(task + " unmarked");
    }
}
