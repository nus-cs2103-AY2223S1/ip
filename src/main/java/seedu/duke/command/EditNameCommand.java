package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskDoesNotExistException;
import seedu.duke.list.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.task.Task;

public class EditNameCommand extends Command {
    private int index;
    private String desc;

    public EditNameCommand(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    @Override
    public String execute(TaskList list) throws DukeException {
        int len = list.size();
        if (index >= len) {
            throw new TaskDoesNotExistException(index);
        }
        Task task = list.get(index);
        task.changeDesc(desc);
        return Ui.nameEdited(index + 1, task);
    }
}
