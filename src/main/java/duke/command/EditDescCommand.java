package duke.command;

import duke.Storage;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidIndexException;
import duke.task.TaskList;

public class EditDescCommand extends Command {
    private int index;
    private String description;

    public EditDescCommand(int index, String description) throws InvalidIndexException, InvalidDescriptionException {
        if (index < 0 || index > TaskList.length() - 1) {
            throw new InvalidIndexException();
        }
        if (description.isEmpty()) {
            throw new InvalidDescriptionException();
        }

        this.index = index;
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.editDesc(this.index,  this.description);
        storage.saveTaskFile(taskList);
    }
}
