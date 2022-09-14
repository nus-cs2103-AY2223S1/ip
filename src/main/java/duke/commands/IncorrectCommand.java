package duke.commands;

import duke.task.TaskList;

import static duke.ui.Messages.MESSAGE_INCORRECT_COMMAND;

public class IncorrectCommand extends Command {
    /**
     * Print error message for invalid command.
     */
    public String execute(TaskList taskList) {
        return MESSAGE_INCORRECT_COMMAND;
    }
}
