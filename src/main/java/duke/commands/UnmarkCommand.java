package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to unmark this task: ";

    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
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
            tasks.markAsNotDone(taskIndex);
            ui.showMessage(MESSAGE_SUCCESS + System.lineSeparator() + tasks.getTask(taskIndex) + tasks.showNumberOfTasks());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}