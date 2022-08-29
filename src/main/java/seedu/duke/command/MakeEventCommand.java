package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.task.EventTask;
import seedu.duke.task.Task;
import seedu.duke.operations.Ui;

import java.time.format.DateTimeParseException;

public class MakeEventCommand extends MakeTaskCommand {

    public MakeEventCommand(String detail) {
        super(detail);
    }

    @Override
    Task makeNewTask(String detail, Ui ui) throws DukeException {
        if (detail.isBlank()) {
            throw new DukeException(ui.getNoDescriptionErrorMessage());
        } else if (!detail.contains("/") || detail.indexOf('/') == detail.length() - 1) {
            throw new DukeException(ui.getNoTimeErrorMessage());
        } else {
            try {
                String description = detail.substring(0, detail.indexOf('/'));
                String datetime = detail.substring(detail.indexOf('/') + 1);
                return new EventTask(description, datetime);
            } catch (DateTimeParseException e) {
                throw new DukeException(ui.getInvalidTimeFormatErrorMessage());
            }
        }
    }
}
