package Command;

import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.TaskList;

import java.io.IOException;

public class UpdateCommand extends Command {

    boolean isExit;
    private String userAction;

    public UpdateCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            String[] updateString = userAction.split(" ", 2);
            int index = Integer.parseInt(updateString[0]);
            String newDescription = updateString[1];
            String updateMessage = tasks.updateTask(index, newDescription);
            storage.save();
            return updateMessage;
        } catch (NumberFormatException e1) {
            throw new DukeException(DukeUi.INVALID_INDEX);
        } catch (IndexOutOfBoundsException e2) {
            throw new DukeException(DukeUi.INVALID_DESCRIPTION);
        } catch (IOException e3) {
            throw new DukeException(e3.getMessage());
        } catch (DukeException e4) {
            return e4.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a find command : find " + userAction;
    }
}
