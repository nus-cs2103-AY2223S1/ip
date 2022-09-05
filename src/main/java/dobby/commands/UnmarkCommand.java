package dobby.commands;

import dobby.DobbyChat;
import dobby.DobbyList;
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
        } catch (StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch (NumberFormatException e) {
            DobbyChat.noNumber();
        } catch (IndexOutOfBoundsException e) {
            DobbyChat.tooLittleTasks();
        }
    }
}
