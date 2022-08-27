package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.DukeCommandNotFoundException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeCommandNotFoundException {
        throw new DukeCommandNotFoundException();
    }
}
