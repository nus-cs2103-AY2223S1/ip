package duke.handlers;

import duke.exceptions.DukeException;
import duke.service.Service;

public class ListHandler implements IHandler{
    public ListHandler() {
    }

    /**
     * Handles the "list" command which lists all tasks currently in the user's tasklist.
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public void handle(Service s) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        int n = s.list.size();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(String.format("%d. %s", i + 1, s.list.get(i)));
            if (i != n - 1) {
                stringBuilder.append("\n");
            }
        }
        s.ui.customPrint(stringBuilder.toString());
    }
}
