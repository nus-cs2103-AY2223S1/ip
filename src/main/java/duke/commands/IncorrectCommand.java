package duke.commands;

import duke.task.TaskList;

public class IncorrectCommand extends Command {
    /**
     * Print error message for invalid command.
     */
    public String execute(TaskList taskList) {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
