package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Reminder command that returns an appropriate reminder
 */
public class ReminderCommand extends DisplayCommand {
    private TaskList tasks;

    /**
     * Initialises the command to display the upcoming task
     * @param tasks task list containing tasks
     */
    public ReminderCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Generates reminder for the upcoming task. If no upcoming deadlines or event, return the default message
     * @return the upcoming event / deadline
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute() throws DukeException, IOException {
        return tasks.getReminder();
    }
}
