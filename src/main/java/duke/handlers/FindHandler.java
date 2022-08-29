package duke.handlers;

import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.service.Service;

import java.util.ArrayList;

public class FindHandler implements IHandler{
    private String taskName;

    public FindHandler(HandlerFactory factory) {
        this.taskName = factory.taskName;
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
        if (this.taskName == null) {
            throw new DukeException("Please enter a task name!");
        }
        for (Task t: s.list) {
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
        s.ui.customPrint(sb.toString());
    }
}
