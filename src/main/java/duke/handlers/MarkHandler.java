package duke.handlers;

import duke.entities.Task;
import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.service.Service;
import duke.service.Ui;

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
     * @throws DukeException
     */
    @Override
    public String handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `mark 2`");
        }
        try {
            int number = Integer.parseInt(this.taskIndex);
            Tasklist list = s.getList();
            if (number > list.size()) {
                throw new DukeException("Task not found! Please try again.");
            }
            Task item = list.get(number - 1);
            item.setDone(true);
            return "Nice! I've marked this task as done:\n  " + item;
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `mark 2`");
        }
    }
}
