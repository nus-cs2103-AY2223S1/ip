package commands;

import java.time.LocalDate;

import data.Deadline;

/**
 * Command to create a deadline.
 */
public class DeadlineCommand extends NewTaskCommand {
    /**
     * Returns a new command to add deadline with title and dateBy.
     *
     * @param title  Title of task.
     * @param dateBy Deadline of task.
     */
    public DeadlineCommand(String title, LocalDate dateBy) {
        super(new Deadline(title, false, dateBy));
    }
}
