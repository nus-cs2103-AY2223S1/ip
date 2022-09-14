package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;

/**
 * This class performs mark a specified task in TaskList as undone Command.
 */
public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
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
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.getTask(index).markAsUndone();
        storage.saveToFile(taskList);
        return Ui.formatMarkAsUndoneMessage(index, taskList.getTask(index));
    }
}
