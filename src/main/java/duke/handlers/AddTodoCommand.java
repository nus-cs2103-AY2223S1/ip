package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;
import duke.models.Todo;

import java.util.Objects;

/**
 * Represents a command to add a to-do.
 */
public class AddTodoCommand implements DukeCommand {

    /**
     * Adds an to-do to the tasklist.
     *
     * @param taskList The tasklist to which the to-do is added to.
     * @param content The user input specifying the detail of the to-do to be added.
     * @return The response containing message about the added to-do.
     * @throws DukeException If an error occurs during parsing the user input.
     */
    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        if (Objects.equals(content, "")) {
            throw new DukeException("Todo must not be empty!");
        }
        Todo todo = new Todo(content);
        taskList.addTask(todo);
        return new DukeResponse("Added a todo: " + todo);
    }
}
