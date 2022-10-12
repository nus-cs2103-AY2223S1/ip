package duke.command;

import duke.task.TaskList;

public class SortTasksCommand extends Command {
    private final String sortType;
    private final boolean isAscending;

    /**
     * Initialises a {@code SortTasksCommand} with a sort type (alphabetically/chronologically)
     * and sort order (ascending/descending).
     *
     * @param sortType The type of sort to execute (alphabetically/chronologically).
     * @param isAscending Whether the sort is to be done in ascending or descending order.
     */
    public SortTasksCommand(String sortType, boolean isAscending) {
        this.sortType = sortType;
        this.isAscending = isAscending;
    }

    @Override
    public String execute(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        }

        String formattedSortType;
        switch (sortType) {
        case "a":
        case "alphabet":
        case "alphabetically":
            formattedSortType = "alphabetically";
            break;
        case "t":
        case "time":
        case "chronologically":
        default:
            formattedSortType = "chronologically";
        }
        String formattedSortOrder = isAscending
                ? "ascending"
                : "descending";

        tasks.sort(sortType, isAscending);

        return String.format("Here is the %s sorted list of tasks in %s order:\n%s",
                formattedSortType, formattedSortOrder, tasks);
    }
}
