package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Event;

import java.time.LocalDate;

/**
 * Encapsulates the command to add an event.
 */
public class AddEventCommand extends Command {
    protected LocalDate date;
    protected String taskName;

    /**
     * Create a AddEventCommand.
     *
     * @param taskName The event name.
     * @param date The event date.
     */
    public AddEventCommand( String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Add the event.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.taskName, this.date);
        tasks.add(event);
        ui.showcase("Nephew so busy, got new event:", event.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
