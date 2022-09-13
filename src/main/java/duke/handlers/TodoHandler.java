package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;
import duke.models.Todo;

import static duke.services.Ui.dukePrint;

public class TodoHandler {
    /**
     * Handles the Todo Duke command.
     * Adds a Todo into the provided list containing description provided in input.
     *
     * @return Response of the executed Todo Command.
     * @param list: TaskList to add a todo in.
     * @param input: Todo Description.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(input);
        list.add(newTask);
        return ("Todo Added!");
    }
}
