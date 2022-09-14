package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;

/**
 * This class performs mark a specified task in TaskList as done command.
 */
public class MarkCommand implements Command {
    public static final String COMMAND_WORD = "mark";
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
        return Ui.formatMarkAsDoneMessage(index, taskList.getTask(index));
    }
}
