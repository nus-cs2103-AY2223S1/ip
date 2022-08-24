package duke.command;

import duke.task.TaskList;

/**
 * Command to search tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for {@code FindCommand}.
     *
     * @param keyword Search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches the {@code TaskList} using the given keyword, and prints the tasks found, if any.
     */
    @Override
    public void execute() {
        TaskList tasksFound = Command.taskList.find(keyword);
        if (tasksFound.size() == 0) {
            Command.ui.printMessages(new String[]{"No tasks found with keyword: " + keyword});
        } else {
            String[] toPrint = new String[tasksFound.size() + 1];
            toPrint[0] = "Tasks found with keyword: " + keyword;
            int i = 1;
            for (String line : tasksFound.getAllTasksInDisplayFormat()) {
                toPrint[i] = line;
                i++;
            }
            Command.ui.printMessages(toPrint);
        }
    }
}
