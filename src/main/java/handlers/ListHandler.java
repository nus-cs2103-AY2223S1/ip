package handlers;

import models.TaskList;

import static services.Ui.dukePrint;

public class ListHandler {
    public static void handle(TaskList list){
        dukePrint("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            dukePrint(i + 1 + ". " + list.get(i));
        }
    }
}
