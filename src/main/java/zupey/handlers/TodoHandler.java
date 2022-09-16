package zupey.handlers;

import zupey.entities.Task;
import zupey.entities.Todo;
import zupey.exceptions.ZupeyException;
import zupey.service.Service;

/** Handles user action of creating a new Todo. */
public class TodoHandler implements IHandler {
    private String todoName;

    public TodoHandler(HandlerFactory factory) {
        this.todoName = factory.getTaskName();
    }

    /**
     * Handles the "todo" command which adds a new Todo to the users task list.
     *
     * @param s Service object of the application
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        if (this.todoName == null) {
            throw new ZupeyException("Please enter a task name!");
        }
        Task todo = new Todo(this.todoName);
        s.saveTasks();
        s.addToList(todo);
        int size = s.getList().size();
        assert size != 0;
        return String.format("Got it. I've added this task:\n  "
                + todo
                + "\nNow you have %d task%s in the list.", size, size != 1 ? "s" : "");
    }
}
