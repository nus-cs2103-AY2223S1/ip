package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to delete this task: \n";

    private int taskIndex;

    public DeleteCommand(int taskIndex) {
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
            tasks.deleteTask(taskIndex);
            ui.showMessage( MESSAGE_SUCCESS + tasks.getTask(taskIndex) + System.lineSeparator() + tasks.showNumberOfTasks());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}