package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;
import duke.models.Todo;

/**
 * A handler class for Todo Commands.
 */
public class TodoHandler {
    /**
     * Handles the Todo Duke command.
     * Adds a Todo into the provided list containing description provided in input.
     *
     * @param list TaskList to add a todo in.
     * @param input Todo Description.
     * @return Response of the executed Todo Command.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(input);
        list.add(newTask);
        return ("Todo Added!");
    }
}
