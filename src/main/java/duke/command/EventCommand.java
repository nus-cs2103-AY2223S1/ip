package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * EventCommand is a Command that creates a new Event.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate at;

    /**
     * Constructor for EventCommand.
     *
     * @param description Description of the Event.
     * @param at The date/time when the Event happens.
     */
    public EventCommand(String description, LocalDate at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Returns the response from Duke after creating a new Event with the given description and time/date.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addEvent(description, at);
        storage.save(tasks.saveTasks());

        return ui.showAdd(task, tasks.getSize());
    }

    /**
     * Returns whether some other object is equal to this one.
     *
     * @param obj Some other object.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand) {
            EventCommand other = (EventCommand) obj;
            return this.description.equals(other.description) && this.at.equals(other.at);
        }
        return false;
    }
}
