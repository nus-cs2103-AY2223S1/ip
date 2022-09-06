package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Response;
import duke.task.Deadline;
import duke.util.TaskList;

/**
 * Handles the command 'deadline'.
 */
public class DeadlineCommand extends Command {
    private String input;
    private String[] segments;
    private final String DEADLINE_ERROR_MESSAGE = "Please follow the format \n'deadline task /by YYYY-MM-DD'!";

    /**
     * Constructor for a new Deadline command.
     *
     * @param input the input
     */
    public DeadlineCommand(String input) {
        this.input = input;
        this.segments = input.split("/by");
    }

    public boolean isValidDeadline() {
        return segments.length == 2;
    }

    /**
     * Runs the command 'deadline'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for deadline.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        try {
            if (isValidDeadline()) {
                assert segments.length == 2 : "A deadline task follows the format 'deadline task /by YYYY-MM-DD'";
                String time = segments[1].strip();
                LocalDate date = LocalDate.parse(time);
                Deadline deadline = new Deadline(segments[0], date);
                taskList.createTask(deadline, builder);
            } else {
                throw new DukeException("Please follow the format \n'deadline task /by YYYY-MM-DD'!");
            } else {
                throw new DukeException(DEADLINE_ERROR_MESSAGE);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(DEADLINE_ERROR_MESSAGE);
        }
    };
}
