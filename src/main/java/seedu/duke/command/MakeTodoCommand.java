package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TodoTask;
import seedu.duke.operations.Ui;

/**
 * Command that handles the "todo" user input. Creates a TodoTask
 * that is then added into TaskList.
 */
public class MakeTodoCommand extends MakeTaskCommand {

    /**
     * Constructor for this Command.
     *
     * @param detail
     */
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
