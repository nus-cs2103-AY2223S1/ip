package roger.commands;

import java.time.LocalDate;

import roger.storage.Storage;
import roger.tasks.Deadline;
import roger.tasks.TaskList;


/**
 * Encapsulates the command to add a deadline.
 */
public class AddDeadlineCommand extends Command {
    protected LocalDate date;
    protected String taskName;

    /**
     * Create a AddDeadlineCommand.
     *
     * @param taskName The deadline name.
     * @param date The due date.
     */
    public AddDeadlineCommand(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Add the deadline.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(this.taskName, this.date);
        tasks.add(deadline);

        return "Nephew got new deadline, hurry up before I beat you:\n"
                + deadline.toString() + "\n"
                + "Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.";
    }
}
