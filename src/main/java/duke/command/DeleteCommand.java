package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidIndexException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) throws InvalidIndexException {
        if (index < 0 || index > TaskList.length() - 1) {
            throw new InvalidIndexException();
        }
        this.index = index;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeTask(index);
        storage.saveTaskFile(taskList);
    }
}
