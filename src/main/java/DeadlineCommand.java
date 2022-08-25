import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Command used to create a new deadline
 */
public class DeadlineCommand extends Command {
    private final String[] inputStrings;

    DeadlineCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1) {
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] deadlineStrings = inputStrings[1].split(" /by ", 2);
        if (deadlineStrings.length == 1 || deadlineStrings[1].equals("")) {
            throw new DukeException("     ☹ OOPS!!! The date/time of a deadline cannot be empty.");
        }
        try {
            Deadline deadline = new Deadline(deadlineStrings[0], false,
                    LocalDateTime.parse(deadlineStrings[1], Task.dateTimeParser));
            tasks.addTask(deadline);

            ui.showAddTask(deadline, tasks.size());
        } catch (DateTimeParseException exception) {
            throw new DukeException("     ☹ OOPS!!! The datetime specified is invalid, it should have the format "
                    + Task.DATE_TIME_INPUT_FORMAT);
        }
    }

    public boolean isExit() {
        return false;
    }
}
