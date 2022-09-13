package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import java.io.IOException;

/**
* Command that marks a specified task as complete when executed.
*/
public class MarkCommand extends Command {

    boolean isExit;
    private String userAction;

    public MarkCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(this.userAction);
            String markTaskAsDoneMessage = tasks.markTaskAsDone(index);
            storage.save();
            return markTaskAsDoneMessage;
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
        return "this is a mark command : mark " + userAction;
    }
}

