package duke.handlers;

import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class ListHandler {
    /**
     * Handles the list Duke command.
     * Prints the list of Tasks from the param list.
     * @param list: TaskList generated from the Tasks.
     **/
    public static void handle(TaskList list){
        dukePrint("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    public static String getResponse(TaskList list){
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            response.append(i).append(". ").append(list.get(i)).append("\n");
        }
        return response.toString();
    }
}
