package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TodoTask;
import seedu.duke.operations.Ui;

public class MakeTodoCommand extends MakeTaskCommand {

    public MakeTodoCommand(String detail) {
        super(detail);
    }

    @Override
    Task makeNewTask(String details, Ui ui) throws DukeException {
        if (details.isBlank()) {
            throw new DukeException(ui.getNoDescriptionErrorMessage());
        }
        return new TodoTask(details);
    }
}
