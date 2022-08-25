package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class ExitCommand extends Command {
    private final String input;

    public ExitCommand(String input) {
        this.input = input;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        System.exit(0);
    }
}
