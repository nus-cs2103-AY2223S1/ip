package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * A class for the list command.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printList();
    }
}
