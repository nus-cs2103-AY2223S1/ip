package roger.commands;

import java.time.LocalDate;

import roger.storage.Storage;
import roger.tasks.Event;
import roger.tasks.TaskList;


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
    public AddEventCommand(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Add the event.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    public String execute(TaskList tasks, Storage storage) {
        Event event = new Event(this.taskName, this.date);
        tasks.add(event);
        return "Nephew so busy, got new event:\n"
                + event.toString() + "\n"
                + "Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.";
    }
}
