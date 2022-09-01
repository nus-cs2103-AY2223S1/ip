package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDate;

/**
 * EventCommand is the command to add an event to the TaskList.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructor for EventCommand.
     *
     * @param description Description of the task.
     * @param time Time of the task.
     */
    public EventCommand(String description, LocalDate time) {
        this.event = new Event(description, time);
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.event);
        storage.saveList(list.save());
        return ui.showAdd(this.event, list.getSize());
    }
}
