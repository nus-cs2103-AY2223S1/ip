package anya;

import java.util.List;

/**
 * Represents a command to find matching task in the current tasklist that contains the keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the find command.
     * @param tasks current tasklist.
     * @param ui interaction class.
     * @return the response of Anya.
     */
    String execute(TaskList tasks, Ui ui) {
        List<Task> matchingTasks = tasks.findTaskWithThisKeyword(this.keyword);
        String response = ui.printMatchingList(matchingTasks);
        return response;
    }

}
