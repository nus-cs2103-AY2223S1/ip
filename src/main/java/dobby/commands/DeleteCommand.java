package dobby.commands;

import java.io.IOException;

import dobby.Dobby;
import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.DobbyStorage;
import dobby.UserInput;


/**
 * Class that deletes tasks in the list.
 */
public class DeleteCommand extends Command {
    /**
     * Executes the delete command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            int toDelete = ui.getInd();
            if (dl.getLength() == 0) {
                DobbyChat.noTaskToDelete();
            } else if (toDelete > dl.getLength()) {
                DobbyChat.tooLittleTasks();
            } else if (toDelete <= 0) {
                DobbyChat.wrongTaskNumber();
            } else {
                DobbyChat.deleted(dl.getTask(toDelete), dl);
                dl.delete(toDelete);
            }
            DobbyStorage.save(dl, Dobby.getFilePath());
        } catch (StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
