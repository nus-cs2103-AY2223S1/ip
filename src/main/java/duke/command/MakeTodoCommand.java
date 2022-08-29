package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TodoTask;
import duke.operations.Ui;

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
