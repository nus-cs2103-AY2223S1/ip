package duke.command;

import duke.DukeException;
import duke.task.Task;

public class MarkCommand extends IndexedCommand {
    MarkCommand(int index) {
        super(index);
    }

    @Override
    void execute() throws DukeException {
        Task task = taskList.markTask(super.index);
        Command.storage.updateLine(index, task.toStorageFormat());
        Command.ui.displayMarkTaskMessage(task);
    }
}
