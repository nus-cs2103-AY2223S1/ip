package duke.command;

import duke.TaskList;
import duke.models.Task;

/**
 * Parses the command entered by the user
 */
public class CommandParser {

    /**
     * Takes in a ${Command} object and returns the result
     * @param commandResult
     * @param taskList
     * @param t
     * @return
     */
    public static String parseCommand(CommandResult commandResult, TaskList taskList, Task t) {
        switch (commandResult) {
        case SUCCESSFUL_ADD:
            return "Got it. I've added this task:\n" + t + "\nNow you have";
        case SUCCESSFUL_DELETE:
            break;
        case SUCCESSFUL_MARK:
            return "Nice! I've marked this task as done\n" + t;
        case SUCCESSFUL_UNMARK:
            break;
        default:
            return "INVALID COMMAND";
        }

        return "INVALID COMMAND";
    }

}
