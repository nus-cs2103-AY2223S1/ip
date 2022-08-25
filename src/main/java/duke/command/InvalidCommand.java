package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command{


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new DukeException("Sorry. I don't understand your command!!!").getMessage());
    }
}
