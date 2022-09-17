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
    private static final String EVENT_ERROR_MESSAGE = "Please follow the format \n'event task /at YYYY-MM-DD'!";
    private String input;
    private String[] segments;

    /**
     * Constructor for a new Event command.
     *
     * @param input the input
     */
    public EventCommand(String input) {
        this.input = input;
        this.segments = input.split("/at");
    }

    public boolean isValidEvent() {
        return segments.length == 2;
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
        try {
            if (isValidEvent()) {
                assert segments.length == 2 : EVENT_ERROR_MESSAGE;
                String time = segments[1].strip();
                LocalDate date = LocalDate.parse(time);
                Event event = new Event(segments[0], date);
                taskList.createTask(event, builder);
            } else {
                throw new DukeException(EVENT_ERROR_MESSAGE);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(EVENT_ERROR_MESSAGE);
        }
    };
}
