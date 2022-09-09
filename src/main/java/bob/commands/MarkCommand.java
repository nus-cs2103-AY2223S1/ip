package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * MarkCommand class to handle "mark" keyword
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructor for MarkCommand
     *
     * @param index index of task in the list to be marked
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTask(this.index, true);
        storage.save(taskList);
        return ui.displayMarked(taskList, this.index);
    }
}
