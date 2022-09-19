package duke.handlers;

import duke.models.TaskList;

/**
 * A handler class for List Commands.
 */
public class ListHandler {
    /**
     * Handles the LIST Duke command.
     * Returns the list of Tasks from the param list.
     *
     * @param list TaskList containing Tasks.
     * @return Response of the executed LIST Command.
     **/
    public static String getResponse(TaskList list) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            response.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return response.toString();
    }
}
