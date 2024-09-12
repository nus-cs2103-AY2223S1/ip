package duke.update;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a NewToDo.
 */
public class NewToDo extends NewTask {

    private String[] newTaskArray;

    /**
     * Creates a ToDo instance.
     * @param newTaskArray The array that represents the ToDo.
     */
    public NewToDo(String[] newTaskArray) {
        this.newTaskArray = newTaskArray;
    }

    /**
     * Create a new ToDo.
     * @return The ToDo created.
     * @throws DukeException If the ToDo cannot be created.
     */
    @Override
    public Task create() throws DukeException {
        if (this.newTaskArray.length < 2) {
            throw new DukeException("The description of a todo cannot be empty!");
        }

        String description = this.newTaskArray[1];
        // create a ToDo object
        Todo todo = new Todo(description);
        return todo;
    }
}

