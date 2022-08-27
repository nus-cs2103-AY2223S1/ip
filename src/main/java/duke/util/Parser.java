package duke.util;

import java.util.Objects;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Parses commands and checks it.
 * @author Jicson Toh
 */
public class Parser {

    /**
     * Checks the input command.
     * @param action user input.
     * @return returns the string action.
     */
    public Command parseCommand(String action) throws DukeException {
        if (Objects.equals(action.strip(), "bye")) {
            return new ByeCommand();
        } else if (Objects.equals(action.strip(), "list")) {
            return new ListCommand();
        } else if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "mark")) {
            return new MarkCommand(action);
        } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "unmark")) {
            return new UnmarkCommand(action);
        } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "delete")) {
            return new DeleteCommand(action);
        } else if (isValidTask(parseTaskType(action))) {
            return new AddCommand(action, parseTaskType(action));
        } else if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "find")) {
            return new FindCommand(action);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if the string is a valid type task.
     * @param taskType input string.
     * @return true is valid task type.
     */
    public boolean isValidTask(String taskType) {
        return taskType.equals("todoTask")
                || taskType.equals("eventTask")
                || taskType.equals("deadlineTask");
    }

    /**
     * Gets the task type of the input.
     * @param action user input.
     * @return returns the type of task.
     */
    public String parseTaskType(String action) {
        if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "todo")) {
            return "todoTask";
        } else if (action.length() >= 5 && Objects.equals(action.substring(0, 5), "event")) {
            return "eventTask";
        } else if (action.length() >= 8 && Objects.equals(action.substring(0, 8), "deadline")) {
            return "deadlineTask";
        } else {
            return "";
        }
    }
}
