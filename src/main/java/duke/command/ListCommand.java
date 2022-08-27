package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand is a Command that displays the list of Tasks.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class ListCommand extends Command {
    /**
     * Returns the list of Tasks.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The list of Tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}
