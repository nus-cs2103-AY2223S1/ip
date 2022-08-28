package duke.commands;

import duke.*;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateCommand extends Command {
    public static final String COMMAND_WORD = "date";

    private String userDescription;
    public DateCommand(String userDescription) {
        this.userDescription = userDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

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
