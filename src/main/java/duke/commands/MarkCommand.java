package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class tells Duke to mark the indexed task as done.
 */
public class MarkCommand implements Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.setTaskDoneStatus(index, true);
            storage.writeToFile(taskList);
            ui.sayMarkTask(index, taskList.getTask(index));
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MarkCommand) {
            MarkCommand that = (MarkCommand) o;
            if (this.index == that.index) {
                return true;
            }
        }
        return false;
    }
}
