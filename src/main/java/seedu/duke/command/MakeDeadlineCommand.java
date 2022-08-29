package seedu.duke.command;

import seedu.duke.task.DeadlineTask;
import seedu.duke.DukeException;
import seedu.duke.task.Task;
import seedu.duke.operations.Ui;

import java.time.format.DateTimeParseException;

/**
 * Command handles "deadline" user inputs. Creates a DeadlineTask that
 * is then added into the TaskList.
 */
public class MakeDeadlineCommand extends MakeTaskCommand {

    public MakeDeadlineCommand(String detail) {
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
                return new DeadlineTask(description, datetime);
            } catch (DateTimeParseException e) {
                throw new DukeException(ui.getInvalidTimeFormatErrorMessage());
            }
        }
    }
}