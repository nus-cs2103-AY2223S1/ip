package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The DateCommand class represents user command date.
 */
public class DateCommand extends Command {
    public static final String COMMAND_WORD = "date";

    private String userDescription;

    /**
     * Constructor for DateCommand that takes in
     * a specified date as a String.
     * @param userDescription Specified date.
     */
    public DateCommand(String userDescription) {
        this.userDescription = userDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDate targetDate = LocalDate.parse(userDescription);
            ArrayList<Task> onDateCount = tasks.getTasksOnDate(targetDate);
            ui.showGetDate(onDateCount);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in yyyy-M-d format.");
        }
    }
}
