package command;

import java.io.IOException;

import wagwan.Storage;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command that marks a specified task as incomplete when executed.
*/
public class UnmarkCommand extends Command {

    private boolean isExit;
    private String userAction;

    /**
     * Constructor for an unmark command.
     * @param userAction the index of the task to be marked as incomplete.
     */
    public UnmarkCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            int index = Integer.parseInt(this.userAction);
            String markTaskAsUndoneMessage = tasks.markTaskAsUndone(index);
            storage.save();
            return markTaskAsUndoneMessage;
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
        return "this is an unmark command : unmark " + userAction;
    }
}
