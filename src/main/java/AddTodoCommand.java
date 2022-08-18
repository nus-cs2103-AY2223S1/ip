import java.util.List;

public class AddTodoCommand implements DukeCommand{
    public String run (List<Task> taskList, String s) throws DukeException {
        if (s == "") {
            throw new DukeException("Todo must not be empty!\n");
        }
        Todo todo = new Todo(s);
        taskList.add(todo);
        return "Added a todo: " + todo.toString();
    }
}
