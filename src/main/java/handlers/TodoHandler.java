package handlers;

import exceptions.DukeException;
import models.Task;
import models.TaskList;
import models.Todo;

import static services.Ui.dukePrint;

public class TodoHandler {
    public static void handle(TaskList list, String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(input);
        list.add(newTask);
        dukePrint("Todo Added!");
    }
}
