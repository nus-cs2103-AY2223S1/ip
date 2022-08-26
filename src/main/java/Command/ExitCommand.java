package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.TaskList;
import java.io.IOException;

/**
* Command that exits the program when executed.
*/
public class ExitCommand extends Command {

    boolean exit;

    public ExitCommand() {
        this.exit = true;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            storage.save();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        ui.endMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "this is an exit command";
    }
}
