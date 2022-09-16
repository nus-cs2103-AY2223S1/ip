package zupey.handlers;

import zupey.entities.Task;
import zupey.entities.Tasklist;
import zupey.exceptions.ZupeyException;
import zupey.service.Service;

/** Handles user action of setting a Task as not done */
public class UnmarkHandler implements IHandler {
    private String taskIndex;

    public UnmarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.getTaskName();
    }

    /**
     * Handles the "unmark" command which marks a task as not done.
     *
     * @param s Service object of the application
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        if (this.taskIndex == null) {
            throw new ZupeyException("Invalid list index!\nUsage: `unmark 2`");
        }
        try {
            Tasklist list = s.getList();
            int number = Integer.parseInt(this.taskIndex);
            Task item = list.get(number - 1);
            s.saveTasks();
            item.setDone(false);
            return "OK, I've marked this task as not done yet:\n  " + item;
        } catch (NumberFormatException ex) {
            throw new ZupeyException("Invalid list index!\nUsage: `unmark 2`");
        }
    }
}
