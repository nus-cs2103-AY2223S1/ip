package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

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
            ui.markTask(index, taskList.getTask(index));
        } catch (DukeException e) {
            ui.handleException(e);
        }
    }
}
