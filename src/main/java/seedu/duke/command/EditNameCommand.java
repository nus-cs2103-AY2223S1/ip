package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
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
        Task task = list.get(index);
        task.changeDesc(desc);
        return Ui.nameEdited(index + 1, task);
    }
}
