package Commands;

import DataStruct.TaskList;


public class FindTasksCommand extends Command {
    private TaskList tasks;
    private String keyword;

    /**
     * Initialises a find task command with a provided tasklist and
     * a keyword to look for within the task list.
     *
     * @param tasks tasklist to search
     * @param keyword keyword to search for in the task list
     */
    public FindTasksCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() {
        return this.tasks.findTasks(keyword);
    }
}
