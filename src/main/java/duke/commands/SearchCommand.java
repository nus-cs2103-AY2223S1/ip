package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to search for tasks occurring
 * on a specified date.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    private LocalDate date;

    /**
     * Constructs a Search Command with the date the user wants
     * to search.
     *
     * @param date Date to be searched for.
     * @throws DateTimeParseException If date is in an invalid format.
     */
    public SearchCommand(LocalDate date) throws DateTimeParseException {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.search(date);
    }
}
