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

    /**
     * Constructor for a new Deadline command.
     *
     * @param input the input
     */
    public DeadlineCommand(String input) {
        this.input = input;
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
        String[] segments = input.split("/by");
        try {
            if (segments.length != 2) {
                throw new DukeException("Please follow the format \n'deadline task /by YYYY-MM-DD'!");
            } else {
                String time = segments[1].strip();
                LocalDate date = LocalDate.parse(time);
                Deadline deadline = new Deadline(segments[0], date);
                taskList.createTask(deadline, builder);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the format \n'deadline task /by YYYY-MM-DD'!");
        }
    };
}
