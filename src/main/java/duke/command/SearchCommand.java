package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to search for tasks occurring
 * on a specified searchDate.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    private LocalDate searchDate;

    /**
     * Constructs a Search Command with the searchDate the user wants
     * to search for.
     *
     * @param searchDate Date to be searched for.
     * @throws DateTimeParseException If searchDate is in an invalid format.
     */
    public SearchCommand(LocalDate searchDate) throws DateTimeParseException {
        this.searchDate = searchDate;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.search(searchDate);
    }
}
