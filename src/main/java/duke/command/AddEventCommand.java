package duke.command;

import duke.Parser;
import duke.exception.DukeException;
import duke.task.Event;

import java.time.LocalDate;

import static duke.exception.ErrorMessage.*;

/**
 * Represents a AddEventCommand to add Event task. This class extends AddCommand class.
 */
public class AddEventCommand extends AddCommand {
    /**
     * This method is a AddEventCommand constructor.
     * A AddEventCommand consists of a TaskList, a description string containing task description, start date and end date.
     * @param taskList a list of tasks.
     * @param description description string containing task description, start date and end date.
     */
    public AddEventCommand(duke.TaskList taskList, String description) {
        super(taskList, description);
    }

    /**
     * Executes add event command to adda Event task to the TaskList.
     * Returns a string containing commandType "event" and task index (1-indexed).
     * Returned value will be used to call chatbot response message.
     *
     * @throws DukeException if the command description string is invalid.
     * @return a string containing commandType "event" and task index.
     */
    public String execute() throws DukeException {
        if ((!description.contains("/at")) || (!description.contains(" to "))) {
            throw new DukeException(INVALID_EVENT_COMMAND, "");
        }
        String taskDescription = description.split("/at")[0].trim();
        if (taskDescription.equals("")) {
            throw new DukeException(MISSING_DESCRIPTION, "event");
        } else if (description.split("/at").length == 1) {
            throw new DukeException(MISSING_DATE, "event");
        }
        String[] dates = description.split("/at")[1].trim().split("to");
        LocalDate start = Parser.parseDate(dates[0]);
        LocalDate end = Parser.parseDate(dates[1]);
        if (start.isAfter(end)) {
            throw new DukeException(INVALID_DATE_RANGE, "");
        }
        super.execute(new Event(taskDescription, start, end));
        return String.format("event %d", super.tasks.getSize());
    }
}
