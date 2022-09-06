package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Response;
import duke.task.Event;
import duke.util.TaskList;

/**
 * Handles the command 'event'.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Constructor for a new Event command.
     *
     * @param input the input
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the command 'event'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for event.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        String[] segments = input.split("/at");
        try {
            if (segments.length != 2) {
                throw new DukeException("Please follow the format \n'event task /at YYYY-MM-DD'!");
            } else {
                assert segments.length == 2 : "A deadline task follows the format 'deadline task /by YYYY-MM-DD'";
                String time = segments[1].strip();
                LocalDate date = LocalDate.parse(time);
                Event event = new Event(segments[0], date);
                taskList.createTask(event, builder);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the format \n'event task /at YYYY-MM-DD'!");
        }
    };
}
