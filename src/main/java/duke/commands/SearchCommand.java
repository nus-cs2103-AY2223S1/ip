package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SearchCommand extends Command {
    LocalDate date;

    public static final String COMMAND_WORD = "search";

    public SearchCommand(LocalDate date) throws DateTimeParseException {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.search(date);
    }
}
