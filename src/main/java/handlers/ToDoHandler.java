package handlers;

import exceptions.DukeException;
import models.*;

import static services.Ui.dukePrint;

public class ToDoHandler {
    public static void handle(TaskList taskList, String input) throws DukeException {
        String toDo = input;
        if (toDo.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        taskList.add(new Todo(toDo));
        dukePrint("Got it. I've added this task:\n" + taskList.get(taskList.size()-1).toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
