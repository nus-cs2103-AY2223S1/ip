package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    private LocalDate date;

    public SearchCommand(LocalDate date) throws DateTimeParseException {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.search(date);
    }
}
