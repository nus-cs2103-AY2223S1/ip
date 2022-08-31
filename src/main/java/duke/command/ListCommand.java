package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showOutput("Here are the tasks in your list: \n" + tasks);
    }
}
