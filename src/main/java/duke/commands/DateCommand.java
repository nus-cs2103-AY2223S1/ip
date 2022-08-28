package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

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
            storage.save(tasks.getTaskList());
            ui.showGetDate(onDateCount);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in yyyy-M-d format.");
        }
    }
}
