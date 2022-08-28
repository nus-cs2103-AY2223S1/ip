package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand is a Command that displays the TaskList.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class ListCommand extends Command {
    /**
     * A constructor for ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * A method that displays the TaskList.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
