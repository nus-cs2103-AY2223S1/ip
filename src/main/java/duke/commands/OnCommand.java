package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class OnCommand extends Command {
    private String input;

    public OnCommand(String input) {
        this.input = input.trim();
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList tasksOnDate = taskList.getTasksOnDate(input);
        ui.showTasksOnDate(tasksOnDate, input);
    }

    public boolean isExit() {
        return false;
    }
}
