package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(String description) throws DukeException {
        try {
            this.index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException();
        }
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        try {
            Task markedTask = taskList.markTaskWithIndex(this.index);
            ui.printTaskMarkSuccessMessage(markedTask);
            storage.saveTasksInStorage(taskList.toStorageRepresentation());
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException();
        }
    }
}
