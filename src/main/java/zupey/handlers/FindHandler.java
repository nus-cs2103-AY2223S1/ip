package zupey.handlers;

import java.util.List;

import zupey.entities.Task;
import zupey.entities.Tasklist;
import zupey.exceptions.ZupeyException;
import zupey.service.Service;

/**
 * Handles the user action for finding a task.
 */
public class FindHandler implements IHandler {
    private String taskName;

    /**
     * Constructs a FindHandler from HandlerFactory
     *
     * @param factory HandlerFactory
     */
    public FindHandler(HandlerFactory factory) {
        this.taskName = factory.getTaskName();
    }

    /**
     * Handles the "find" command which finds the task using the keyword input from the user.
     *
     * @param s Service object of the application
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        if (this.taskName == null) {
            throw new ZupeyException("Please enter a task name!");
        }
        Tasklist list = s.getList();
        List<Task> results = list.find(this.taskName);
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Task t: results) {
            if (index > 1) {
                sb.append("\n");
            }
            sb.append(index + ". ");
            sb.append(t);
            index++;
        }
        return sb.toString();
    }
}
