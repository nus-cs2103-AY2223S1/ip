package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.TaskList;
import duke.models.Todo;

public class ToDoHandler {
    /**
     * Handles the TODO command.
     * Adds ToDo task to TaskList.
     * @param taskList TaskList for the event task to be added to.
     * @param input Event to be added to TaskList.
     */
    public static String getResponse(TaskList taskList, String input) throws DukeException {
        String toDo = input;
        if (toDo.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        taskList.add(new Todo(toDo));
        return ("Got it. I've added this task:\n"
                + taskList.get(taskList.size()-1).toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
