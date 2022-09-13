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

    boolean isExit;

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            storage.save();
        } catch (IOException e1) {
            throw new DukeException(e1.getMessage());
        } catch (DukeException e2) {
            return e2.toString();
        }
        return ui.endMessage();
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
