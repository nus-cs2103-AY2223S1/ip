package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulation of the command of listing out the tasks in the list.
 *
 * @author Sun Ruoxin
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     * Show the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
//        ui.listMessage(tasks);
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
//            say(i + 1 + ". " + tasks.get(i).toString(), isFirstLine, isLastLine);
            result += i + 1 + ". " + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Returns a boolean value representing whether to exit the programme
     * after the command is executed.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
