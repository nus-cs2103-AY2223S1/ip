package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExitCommand extends Command {

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ui.showGoodbye();
        System.exit(0);
    }
}
