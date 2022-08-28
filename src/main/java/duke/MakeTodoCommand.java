package duke;

public class MakeTodoCommand extends MakeTaskCommand {

    MakeTodoCommand(String detail) {
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
