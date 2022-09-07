package duke.update;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Task;
import duke.task.Todo;

public class ToDoUpdate extends NewTask {

    private String[] newTaskArray;

    public ToDoUpdate(String[] newTaskArray) {
        this.newTaskArray = newTaskArray;
    }

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

