package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Adds a person to the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to mark this task done: ";

    private int taskIndex;

    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(taskIndex);
            ui.showMessage(MESSAGE_SUCCESS + System.lineSeparator() + tasks.getTask(taskIndex) + tasks.showNumberOfTasks());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}