package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class tells Duke to store a new event.
 */
public class EventCommand implements Command {

    /** The event to add. */
    private Event event;

    /**
     * Constructs a EventCommand object.
     *
     * @param desc The description of the deadline task.
     * @param dateTime The due date and time of the task.
     */
    public EventCommand(String desc, LocalDateTime dateTime) {
        event = new Event(desc, dateTime);
    }

    /**
     * Executes the event command from the user.
     *
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(event);
            storage.appendToFile(event);
            ui.sayAddTask(event);
            ui.sayTaskListSize(taskList);
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof EventCommand) {
            EventCommand that = (EventCommand) o;
            if (event.equals(that.event)) {
                return true;
            }
        }
        return false;
    }
}
