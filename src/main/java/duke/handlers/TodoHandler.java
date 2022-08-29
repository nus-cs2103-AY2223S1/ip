package duke.handlers;

import duke.entities.Task;
import duke.entities.Todo;
import duke.exceptions.DukeException;
import duke.service.Service;

public class TodoHandler implements IHandler{
    private String todoName;

    public TodoHandler(HandlerFactory factory) {
        this.todoName = factory.taskName;
    }

    /**
     * Handles the "todo" command which adds a new Todo to the users task list.
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public void handle(Service s) throws DukeException {
        if (this.todoName == null) {
            throw new DukeException("Please enter a task name!");
        }
        Task todo = new Todo(this.todoName);
        s.addToList(todo);
    }
}
