package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Describes the handler for the Todo command.
 */
public class CommandTodoHandler extends CommandHandler {

    public CommandTodoHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        boolean isValid = value != null && flag == null && additionalValue == null;
        if (isValid) {
            assert !value.contains("|");
            return;
        }
        throw new InvalidSyntaxException("Correct usage: todo read book");
    }

    /**
     * Handles the execution of the Todo command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the Todo command.
     */
    public String handle(TaskList taskList) {
        Task task = new ToDo(this.value);
        return taskList.addTask(task);
    }
}
