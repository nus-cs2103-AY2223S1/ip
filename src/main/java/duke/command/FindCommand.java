package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initialises a {@code FindCommand} with the {@code String} keyword.
     *
     * @param keyword The {@code String} to search the {@code TaskList} with.
     */
    public FindCommand(String keyword) {
        assert !keyword.isBlank();
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        TaskList matchingList = tasks.findByKeyword(keyword);
        return String.format("Here are the matching tasks in your list:\n%s",
                matchingList);
    }
}
