package duke.command;

import duke.Parser;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.time.LocalDate;

import static duke.exception.ErrorMessage.INVALID_DEADLINE_COMMAND;
import static duke.exception.ErrorMessage.MISSING_DESCRIPTION;
import static duke.exception.ErrorMessage.MISSING_DATE;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(duke.TaskList taskList, String description) {
        super(taskList, "deadline", description);
    }

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