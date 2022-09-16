package zupey.handlers;

import zupey.entities.Tasklist;
import zupey.exceptions.ZupeyException;
import zupey.service.Service;

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
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        StringBuilder stringBuilder = new StringBuilder();
        Tasklist list = s.getList();
        if (list.size() == 0) {
            return "You have no tasks in your list!";
        }
        stringBuilder.append("Here are the tasks in your list:\n");
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
