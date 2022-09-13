package duke.command;

import duke.Parser;
import duke.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initialises a {@code FindCommand} with the {@code String} keyword.
     *
     * @param keyword The {@code String} to search the {@code TaskList} with.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks) {
        TaskList matchingList = tasks.findByKeyword(keyword);
        Parser.printMsg(String.format("Here are the matching tasks in your list:\n%s",
                matchingList));
    }
}
