package duke.command;

import duke.task.TaskList;

public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks in the task list as a list with index numbers. Example: " + COMMAND_WORD;

    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
