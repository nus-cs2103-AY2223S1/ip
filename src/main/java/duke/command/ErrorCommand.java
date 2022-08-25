package duke.command;

import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;

public class ErrorCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.repeater("Sorry, I do not accept that as a task!");
    }
}
