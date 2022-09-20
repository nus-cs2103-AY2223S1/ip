package zupey.handlers;

import zupey.entities.Task;
import zupey.entities.Tasklist;
import zupey.exceptions.ZupeyException;
import zupey.service.Service;

/** Handles the user action for marking a Task as done */
public class MarkHandler implements IHandler {
    private String taskIndex;

    public MarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.getTaskName();
    }

    /**
     * Handles the "mark" command which marks a task as done.
     *
     * @param s Service object of the application
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        if (this.taskIndex == null) {
            throw new ZupeyException("Invalid list index!\nUsage: `mark 2`");
        }
        try {
            int number = Integer.parseInt(this.taskIndex);
            Tasklist list = s.getList();
            if (number > list.size()) {
                throw new ZupeyException("Task not found! Please try again.");
            }
            Task item = list.get(number - 1);
            s.saveTasks();
            item.setDone(true);
            return "Nice! I've marked this task as done:\n  " + item;
        } catch (NumberFormatException ex) {
            throw new ZupeyException("Invalid list index!\nUsage: `mark 2`");
        }
    }
}
