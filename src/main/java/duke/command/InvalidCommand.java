package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import exception
import duke.exception.DukeCommandNotFoundException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeCommandNotFoundException {
        throw new DukeCommandNotFoundException();
    }
}
