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
    private static final String ALREADY_MARKED_MESSAGE = "Task has already been marked";
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
        if (taskList.getTask(index).isDone()) {
            return ALREADY_MARKED_MESSAGE;
        }
        taskList.getTask(index).markAsDone();
        storage.saveToFile(taskList);
        return Ui.formatMarkAsDoneMessage(index, taskList.getTask(index));
    }
}
