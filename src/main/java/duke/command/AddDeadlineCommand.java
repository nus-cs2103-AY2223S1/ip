package duke.command;

import duke.Parser;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.time.LocalDate;

import static duke.exception.ErrorMessage.INVALID_DEADLINE_COMMAND;
import static duke.exception.ErrorMessage.MISSING_DESCRIPTION;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(duke.TaskList taskList, String description) {
        super(taskList, "deadline", description);
    }

    public void execute() throws DukeException {
        if (!description.contains("/by")) {
            throw new DukeException(INVALID_DEADLINE_COMMAND, "");
        }
        String taskDescription = description.split("/by")[0].trim();
        if (taskDescription.equals("")) {
            throw new DukeException(MISSING_DESCRIPTION, "deadline");
        }
        LocalDate end = Parser.parseDate(description.split("/by")[1]);
        super.execute(new Deadline(taskDescription, end));
    }
}