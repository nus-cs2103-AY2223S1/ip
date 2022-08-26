package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs mark a specified task in TaskList as undone Command.
 */
public class UnmarkCommand implements Command {
    /** Index of task to be mark as undone */
    private int index;

    /**
     * Constructor for class UnmarkCommand.
     *
     * @param index Index of task to be marked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(index).markAsUndone();
            storage.saveToFile(taskList);
            ui.markAsUndone(index, taskList.getTask(index));
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
