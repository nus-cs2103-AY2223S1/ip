package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs mark a specified task in TaskList as done command.
 */
public class MarkCommand implements Command {
    /** Index of task to be marked as done */
    private int index;

    /**
     * Constructor for class MarkCommand.
     *
     * @param index Index of task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(index).markAsDone();
            storage.storeToFile(taskList);
            ui.markAsDone(index, taskList.getTask(index));
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
