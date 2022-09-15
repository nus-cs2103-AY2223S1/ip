package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * RemindCommand is a Command that handles reminders.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class RemindCommand extends Command {
    /**
     * Constructor for RemindCommand.
     */
    public RemindCommand() {

    }

    /**
     * Outputs the tasks to be reminded.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @return String representation of reminder.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> remindedTasks = tasks.remindTasks();
        return ui.displayReminder(remindedTasks);
    }
}
