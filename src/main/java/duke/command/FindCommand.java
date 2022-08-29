package duke.command;

import duke.task.TaskList;

public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find tasks in the task list. Parameters: DESCRIPTION. Example: " + COMMAND_WORD + " book";

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList) {
        TaskList filteredList = taskList.findTask(description);
        return filteredList.printListWithMessage("Here are the matching tasks in your list:");
    }
}
