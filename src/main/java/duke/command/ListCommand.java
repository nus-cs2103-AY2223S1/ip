package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            throw new DukeException("☹ OOPS!!! You have no tasks in the list.");
        }
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            ui.showMessage((i + 1) + "." + task);
        }
    }
}
