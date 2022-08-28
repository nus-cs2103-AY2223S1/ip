package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;

public class NullCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        return;
    }
}
