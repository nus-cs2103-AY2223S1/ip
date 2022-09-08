package duke;

import java.util.List;

/**
 * Represents a command to sort the deadline by dates and return that sorted list.
 */
public class SortDeadlineCommand extends Command {

    SortDeadlineCommand() {

    }

    /**
     * Execute the sortDeadline command.
     * @param tasks current tasklist.
     * @param ui interaction class.
     * @return the response of the duke.
     */
    String execute(TaskList tasks, Ui ui) {
        List<Deadline> sortedDeadlines = tasks.getSortedDeadlinesList();
        String response = ui.printSortedDeadlineList(sortedDeadlines);
        return response;
    }

}


