package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Deadline;

import java.time.LocalDate;

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
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.taskName, this.date);
        tasks.add(deadline);
        ui.showcase("Nephew got new deadline, hurry up before I beat you:", deadline.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
