package dobby.commands;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;

/**
 * Class that marks tasks in the list.
 */
public class MarkCommand extends Command {
    /**
     * Executes the mark command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            int toMark = ui.getInd();
            if (toMark <= 0) {
                DobbyChat.wrongTaskNumber();
            } else if (dl.getTask(toMark).isDone()) {
                DobbyChat.alreadyMarked();
            } else {
                dl.mark(toMark);
                DobbyChat.marked(dl.getTaskString(toMark));
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
