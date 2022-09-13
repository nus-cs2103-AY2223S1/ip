package duke.command;

import duke.Parser;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.time.LocalDate;

import static duke.exception.ErrorMessage.INVALID_DEADLINE_COMMAND;
import static duke.exception.ErrorMessage.MISSING_DESCRIPTION;
import static duke.exception.ErrorMessage.MISSING_DATE;

/**
 * Represents a AddDeadlineCommand to add Deadline task. This class extends AddCommand class.
 */
public class AddDeadlineCommand extends AddCommand {

    /**
     * This method is a AddDeadlineCommand constructor.
     * A AddDeadlineCommand consists of a TaskList, a description string containing task description and end date.
     * @param taskList a list of tasks.
     * @param description description string containing task description and end date.
     */
    public AddDeadlineCommand(duke.TaskList taskList, String description) {
        super(taskList, description);
    }

    /**
     * Executes add deadline command to adda Deadline task to the TaskList.
     * Returns a string containing commandType "deadline" and task index (1-indexed).
     * Returned value will be used to call chatbot response message.
     *
     * @throws DukeException if the command description string is invalid.
     * @return a string containing commandType "deadline" and task index.
     */
    public String execute() throws DukeException {
        if (!description.contains("/by")) {
            throw new DukeException(INVALID_DEADLINE_COMMAND, "");
        }
        String taskDescription = description.split("/by")[0].trim();
        if (taskDescription.equals("")) {
            throw new DukeException(MISSING_DESCRIPTION, "deadline");
        } if (description.split("/by").length == 1) {
            throw new duke.exception.DukeException(MISSING_DATE, "deadline");
        }
        LocalDate end = Parser.parseDate(description.split("/by")[1]);
        super.execute(new Deadline(taskDescription, end));
        return String.format("deadline %d", super.tasks.getSize());
    }
}