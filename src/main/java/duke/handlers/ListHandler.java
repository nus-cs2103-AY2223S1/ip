package duke.handlers;

import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.service.Service;

/**
 * Handles the user action for listing all tasks.
 */
public class ListHandler implements IHandler {
    public ListHandler() {
    }

    /**
     * Handles the "list" command which lists all tasks currently in the user's tasklist.
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public String handle(Service s) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        Tasklist list = s.getList();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(String.format("%d. %s", i + 1, list.get(i)));
            if (i != n - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
