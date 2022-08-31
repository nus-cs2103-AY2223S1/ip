package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Command to add an event task to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private String description;
    private String duration;

    /**
     * Constructor for the EventCommand Object.
     * @param description The description of the event.
     * @param duration The duration of the event.
     */
    public EventCommand(String description, String duration) {
        this.description = description;
        this.duration = duration;
    }

    /**
     * Creates an event task and add it into the task list and return a message
     * that the event task has been added.
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the event has been added and the number of current tasks.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        Event currEvent = new Event(this.description, this.duration);
        task.addTask(currEvent);
        return "Got it. I've added this task:\n " + currEvent.taskInfo()
                + "\nNow you have"  + task.getTaskSize() + " tasks in the list.";
    }

}
