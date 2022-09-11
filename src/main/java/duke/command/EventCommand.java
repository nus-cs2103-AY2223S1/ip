package duke.command;

import java.time.LocalDate;

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

    /** Description of the event. */
    private String description;

    /** Date the event is at. */
    private LocalDate date;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the event.
     * @param date the date the event is at.
     */
    public EventCommand(TaskList taskList, String description, LocalDate date) {
        this.taskList = taskList;
//        assert description.length() > 0;
        this.description = description;
        this.date = date;
    }

    @Override
    public String get() {
        taskList.add(new Event(description, date));
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}
