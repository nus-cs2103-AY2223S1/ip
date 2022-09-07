package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;

/**
 * EventCommand represents a command to add an Event to the TaskList.
 */
public class EventCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a EventCommand to add an Event to the TaskList.
     *
     * @param taskList The TaskList to add the Event to.
     * @param inputArr The input String array.
     */
    public EventCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Adds the Event to the TaskList.
     *
     * @return The Response to be displayed.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response execute() throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String[] descriptionDate = inputArr[1].split(" /at ", 2);
        if (descriptionDate.length < 2) {
            throw new DukeException("The deadline and date of an event cannot be empty.");
        }
        String description = descriptionDate[0];
        String date = descriptionDate[1];
        Event event = new Event(description, date);
        taskList.addTask(event);
        return new Response("Got it. I've added this task: \n"
                + event + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.\n");
    }
}
