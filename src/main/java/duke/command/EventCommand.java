package duke.command;

import java.time.LocalDate;

import duke.DukeException;
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
     * @throws DukeException Duke Exception for event.
     */
    @Override
    public void run(TaskList taskList) throws DukeException {
        String[] segments = input.split("/at");
        if (segments.length != 2) {
            throw new DukeException("Error with event input");
        } else {
            String time = segments[1].strip();
            LocalDate date = LocalDate.parse(time);
            Event event = new Event(segments[0], date);
            taskList.createTask(event);
        }
    };
}
