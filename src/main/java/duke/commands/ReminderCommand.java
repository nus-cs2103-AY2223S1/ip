package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ReminderCommand extends Command {

    /**
     * Returns a list of reminders for time-critical tasks that need to be completed in the next 3
     * days.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return List of reminders
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getReminders();
    }
}
