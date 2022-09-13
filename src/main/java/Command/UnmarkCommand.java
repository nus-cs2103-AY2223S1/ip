package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import java.io.IOException;

/**
* Command that marks a specified task as incomplete when executed.
*/
public class UnmarkCommand extends Command {
    boolean isExit;
    private String userAction;
    public UnmarkCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(this.userAction);
            String markTaskAsUndoneMessage = tasks.markTaskAsUndone(index);
            storage.save();
            return markTaskAsUndoneMessage;
        } catch (IOException e1) {
            throw new DukeException(e1.getMessage());
        } catch (NumberFormatException e2) {
            throw new DukeException(DukeUi.INVALID_INDEX);
        } catch (IndexOutOfBoundsException e3) {
            throw new DukeException(DukeUi.INDEX_OUT_OF_RANGE);
        } catch (DukeException e4) {
            return e4.toString();
        }
    }

    @Override
    public String toString() {
        return "this is an unmark command : unmark " + userAction;
    }
}

