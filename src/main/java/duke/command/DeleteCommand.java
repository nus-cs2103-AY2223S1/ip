package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor to create a delete command that deletes the task at the specified index when executed.
     *
     * @param index Index of the task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
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
        tasks.delete(this.index);
        storage.overwriteData(tasks);
        ui.notifyDeleted(tasks.get(this.index));
    }
}
