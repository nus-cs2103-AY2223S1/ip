package duke.command;

import duke.task.TaskList;

public class FindTasksCommand extends Command {
    private final String keyword;

    /**
     * Initialises a {@code FindTasksCommand} with the {@code String} keyword.
     *
     * @param keyword The {@code String} to search the {@code TaskList} with.
     */
    public FindTasksCommand(String keyword) {
        assert !keyword.isBlank();
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        TaskList matchingList = tasks.findByKeyword(keyword);
        if (matchingList.isEmpty()) {
            return "No tasks found.";
        }
        return String.format("Here are the matching tasks in your list:\n%s",
                matchingList);
    }
}
