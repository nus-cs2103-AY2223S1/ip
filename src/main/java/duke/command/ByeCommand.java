package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand is a Command that ends the session with Duke.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class ByeCommand extends Command {
    /**
     * A method that displays the farewell message with Duke.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
