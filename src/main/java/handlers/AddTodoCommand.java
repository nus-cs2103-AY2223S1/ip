package handlers;

import exceptions.DukeException;
import models.Task;
import models.Todo;

import java.util.List;
import java.util.Objects;

public class AddTodoCommand implements DukeCommand {
    public String run (List<Task> taskList, String s) throws DukeException {
        if (Objects.equals(s, "")) {
            throw new DukeException("Todo must not be empty!\n");
        }
        Todo todo = new Todo(s);
        taskList.add(todo);
        return "Added a todo: " + todo.toString();
    }
}
