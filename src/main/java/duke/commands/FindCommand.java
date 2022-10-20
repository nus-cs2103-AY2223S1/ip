package duke.commands;

import duke.TaskList;

public class FindCommand implements Command {
    public final static String COMMAND_WORD = "find";

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.searchTasks(searchString);
    }
}
