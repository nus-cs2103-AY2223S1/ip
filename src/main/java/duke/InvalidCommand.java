package duke;

public class InvalidCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.getInvalidInputMessage());
    }
}
