package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printWithIndent("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.taskCount(); i++) {
            Task task = taskList.getTask(i);
            ui.printWithIndent(i + ". " + task);
        }
    }
}
