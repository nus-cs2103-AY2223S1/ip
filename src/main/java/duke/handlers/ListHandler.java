package duke.handlers;

import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class ListHandler {
    public static void handle(TaskList list){
        dukePrint("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
}
