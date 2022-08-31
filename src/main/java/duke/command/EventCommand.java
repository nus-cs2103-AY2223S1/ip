package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TasksList;

/**
 * Represents a command to add an Event to the tasksList.
 */
public class EventCommand extends Command {
    private static final String DELIMITER = " /at ";
    private static final String EVENT_MSG = "Got it. I've added this task:\n";
    private String[] inputArray;
    private TasksList tasksList;

    /**
     * Creates a new EventCommand instance.
     * @param tasksList The TasksList to add the Event to.
     * @param inputArray The array that represents the user input.
     */
    public EventCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Adds the Event to the TasksList.
     * @return The message to be displayed upon the execution of the command.
     * @throws DukeException If the Event cannot be added to the TasksList.
     */
    @Override
    public String execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("The description of a event cannot be empty!");
        }

        // split again to get date/time
        String[] splitArray = this.inputArray[1].split(EventCommand.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Please enter a date and time for this event!");
        }

        // Make a new event object
        Event event = new Event(splitArray[0], splitArray[1]);
        this.tasksList.addToList(event);
        StringBuilder sb = new StringBuilder();
        sb.append(EventCommand.EVENT_MSG + event + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        return sb.toString();
    }

}
