package duke.command;

import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
       ui.list(taskList);
    }
}
