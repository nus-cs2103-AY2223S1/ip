package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.bye();
            storage.writeToFile(taskList);
            ui.exit();
        } catch (DukeException e) {
            ui.handleException(e);
        }
    }
}
