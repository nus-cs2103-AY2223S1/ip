package duke.handlers;

import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class ListHandler {
    /**
     * Handles the LIST Duke command.
     * Returns the list of Tasks from the param list.
     *
     * @return Response of the executed LIST Command.
     * @param list: TaskList containing Tasks.
     **/
    public static String getResponse(TaskList list){
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            response.append(i).append(". ").append(list.get(i)).append("\n");
        }
        return response.toString();
    }
}
