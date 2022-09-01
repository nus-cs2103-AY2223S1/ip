package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tasks.Event;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

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
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        taskList.addTask(event);
        storage.appendToFile(event);
        return GuiText.formatAddTaskString(taskList.getSize() - 1, event);
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
