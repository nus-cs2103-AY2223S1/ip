package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class UnmarkCommand implements Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(index).markAsUndone();
            storage.saveToFile(taskList);
            ui.markAsUndone(index, taskList.getTask(index));
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
