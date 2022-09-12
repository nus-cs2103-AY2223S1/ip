package duke.handlers;

import java.util.ArrayList;

import duke.entities.Task;
import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.service.Service;
import duke.service.Ui;

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
     * @throws DukeException
     */
    @Override
    public void handle(Service s) throws DukeException {
        ArrayList<Task> results = new ArrayList<>();
        Tasklist list = s.getList();
        Ui ui = s.getUi();
        if (this.taskName == null) {
            throw new DukeException("Please enter a task name!");
        }
        for (Task t: list) {
            if (t.toString().contains(this.taskName)) {
                results.add(t);
            }
        }
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
        ui.customPrint(sb.toString());
    }
}
