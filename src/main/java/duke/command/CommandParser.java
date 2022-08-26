package duke.command;

import duke.TaskList;
import duke.models.Task;

public class CommandParser {

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
        }

        return "INVALID COMMAND";
    }

}
