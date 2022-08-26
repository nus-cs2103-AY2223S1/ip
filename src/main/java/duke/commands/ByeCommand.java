package duke.commands;

import duke.exceptions.DukeException;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printFarewell();
            storage.saveToFile(tasks);
            ui.exit();
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
