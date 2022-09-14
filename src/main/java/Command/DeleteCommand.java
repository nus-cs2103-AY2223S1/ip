package Command;
import Duke.WagwanException;
import Duke.WagwanUi;
import Duke.Storage;
import Duke.TaskList;
import java.io.IOException;

/**
* Command that deletes a task from the TaskList when executed.
*/
public class DeleteCommand extends Command {
    boolean isExit;
    private String userAction;

    public DeleteCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            int index = Integer.parseInt(this.userAction);
            String deleteTaskMessage = tasks.deleteTask(index);
            storage.save();
            return deleteTaskMessage;
        } catch (IOException e1) {
            throw new WagwanException(e1.getMessage());
        } catch (NumberFormatException e2) {
            throw new WagwanException(WagwanUi.INVALID_INDEX);
        } catch (IndexOutOfBoundsException e3) {
            throw new WagwanException(WagwanUi.INDEX_OUT_OF_RANGE);
        } catch (WagwanException e4) {
            return e4.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a delete command : delete " + userAction;
    }
}
