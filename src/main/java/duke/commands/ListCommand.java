package duke.commands;

import duke.TaskList;

/**
 * Command for printing list of tasks
 */
public class ListCommand implements Command {
    public final static String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
