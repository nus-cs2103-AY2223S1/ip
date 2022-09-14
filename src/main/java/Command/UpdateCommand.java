package Command;

import Duke.WagwanException;
import Duke.WagwanUi;
import Duke.Storage;
import Duke.TaskList;

import java.io.IOException;

/**
* Command that updates a description of a specified task when executed.
*/
public class UpdateCommand extends Command {

    boolean isExit;
    private String userAction;

    public UpdateCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            String[] updateString = userAction.split(" ", 2);
            int index = Integer.parseInt(updateString[0]);
            String newDescription = updateString[1];
            String updateMessage = tasks.updateTask(index, newDescription);
            storage.save();
            return updateMessage;
        } catch (NumberFormatException e1) {
            throw new WagwanException(WagwanUi.INVALID_INDEX);
        } catch (IndexOutOfBoundsException e2) {
            throw new WagwanException(WagwanUi.INVALID_DESCRIPTION);
        } catch (IOException e3) {
            throw new WagwanException(e3.getMessage());
        } catch (WagwanException e4) {
            return e4.toString();
        }
    }

    @Override
    public String toString() {
        return "this is an update command : " + userAction;
    }
}
