package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor to create a new task command that adds the specified task to the task list and storage when
     * executed.
     *
     * @param task Index of the task in the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Method that executes the mark command to mark the specified task done or not done.
     *
     * @param tasks Task list containing the task to be marked.
     * @param ui Ui that will output messages to the user.
     * @param storage Storage on hard disk that stores the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        storage.writeData(this.task);
        ui.notifyAdded(this.task);
    }
}
