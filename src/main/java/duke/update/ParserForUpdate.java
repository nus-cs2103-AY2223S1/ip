package duke.update;

import duke.exception.DukeException;

/**
 * Deals with making sense of the taskString.
 */
public class ParserForUpdate {

    /**
     * Parses a taskString to a newTask.
     * @param taskString The taskString.
     * @return The corresponding NewTask to the taskString.
     * @throws DukeException If there is no NewTask corresponding to the taskString.
     */
    public static NewTask handleUpdate(String taskString) throws DukeException {
        String[] newTaskArray = taskString.trim().split("\\s+", 2);

        String taskType = newTaskArray[0];
        if (taskType.equals("todo")) {
            return new NewToDo(newTaskArray);
        } else if (taskType.equals("deadline")) {
            return new NewDeadline(newTaskArray);
        } else if (taskType.equals("event")) {
            return new NewEvent(newTaskArray);
        } else {
            throw new DukeException("Please enter a valid Task to update to!");
        }
    }
}
