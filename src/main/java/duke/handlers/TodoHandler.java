package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;
import duke.models.Todo;

import static duke.services.Ui.dukePrint;

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
