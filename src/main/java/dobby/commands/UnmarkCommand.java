package dobby.commands;

import java.io.IOException;

import dobby.Dobby;
import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.DobbyStorage;
import dobby.UserInput;

/**
 * Class that unmarks tasks in the list.
 */
public class UnmarkCommand extends Command {
    /**
     * Executes the unmark command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            int toUnmark = ui.getInd();
            if (toUnmark <= 0) {
                DobbyChat.wrongTaskNumber();
            } else if (!(dl.getTask(toUnmark).isDone())) {
                DobbyChat.alreadyUnmarked();
            } else {
                dl.unmark(toUnmark);
                DobbyChat.unmarked(dl.getTaskString(toUnmark));
            }
            DobbyStorage.save(dl, Dobby.getFilePath());
        } catch (StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch (IndexOutOfBoundsException e) {
            DobbyChat.tooLittleTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
