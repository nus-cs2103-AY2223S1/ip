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
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.getTask(index).markAsDone();
        storage.saveToFile(taskList);
        return Ui.formatMarkAsDoneString(index, taskList.getTask(index));
    }
}
