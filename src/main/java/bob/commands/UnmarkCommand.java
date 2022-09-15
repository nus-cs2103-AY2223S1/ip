package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * UnmarkCommand class to handle "unmark" keyword
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor for UnmarkCommand
     *
     * @param index index of task in the list to be unmarked
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTask(this.index, false);
        storage.save(taskList);
        return ui.displayUnmarked(taskList, this.index);
    }
}
