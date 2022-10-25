package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ErrorCommand extends Command {

    private final String ERROR;

    public ErrorCommand(String error) {
        this.ERROR = error;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ERROR);
    }
}
