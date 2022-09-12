package duke.command;

import java.time.LocalDate;

import duke.exception.DuplicateTaskException;
import duke.logic.TaskList;
import duke.task.Event;

/**
 * EventCommand is a command for Duke to remember an event.
 *
 * @author totsukatomofumi
 */
public class EventCommand extends Command {
    /** Task list the command has to add an event to. */
    private TaskList taskList;

    private Event event;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the event.
     * @param date the date the event is at.
     * @throws DuplicateTaskException If the event specified already exists.
     */
    public EventCommand(TaskList taskList, String description, LocalDate date) throws DuplicateTaskException {
        this.taskList = taskList;
        assert description.length() > 0;
        this.event = new Event(description, date);
        if (this.taskList.contains(this.event)) {
            throw new DuplicateTaskException("duplicate event already exists");
        }
    }

    @Override
    public String get() {
        taskList.add(this.event);
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}
