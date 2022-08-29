package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * Constructor for a command to mark an object done or not done.
     *
     * @param index Index of the task in the task list.
     * @param isDone Boolean of which the specified task will be marked.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
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
        tasks.changeDoneStatus(this.index, this.isDone);
        storage.overwriteData(tasks);
        ui.notifyMarked(tasks.get(this.index), this.isDone);
    }
}
