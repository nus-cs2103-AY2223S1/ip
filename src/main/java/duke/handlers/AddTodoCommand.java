package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.TaskList;
import duke.models.Todo;

import java.util.Objects;

public class AddTodoCommand implements DukeCommand {
    public String run (TaskList taskList, String s) throws DukeException {
        if (Objects.equals(s, "")) {
            throw new DukeException("Todo must not be empty!");
        }
        Todo todo = new Todo(s);
        taskList.AddTask(todo);
        return "Added a todo: " + todo;
    }
}
