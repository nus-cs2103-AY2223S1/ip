package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ScheduleCommand class is a Command that gives users a way
 * to find a task by searching for a keyword.
 *
 * @author Edric Yeo
 */
public class ScheduleCommand extends Command {

    private String date;

    /**
     * Creates a ScheduleCommand instance, given a date.
     *
     * @param date The String representing the formatted date.
     */
    public ScheduleCommand(String date) {
        this.date = date;
    }

    /**
     * Returns a message containing all scheduled tasks.
     * Finds all scheduled tasks.
     *
     * @param tasks   The TaskList instance of the task manager.
     * @param ui      The Ui instance of the task manager.
     * @param storage The Storage instance of the task manager.
     * @return A message displaying all scheduled tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList scheduledTasks = tasks.findTasks(this.date);
        return ui.showTasks(scheduledTasks, "Here are the scheduled tasks in your list:\n");
    }

    /**
     * Returns the String representation of a ScheduleCommand.
     *
     * @return The String representation of the ScheduleCommand.
     */
    @Override
    public String toString() {
        return "Schedule command of date:" + this.date;
    }
}
