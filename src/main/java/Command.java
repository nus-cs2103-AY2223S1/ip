import java.io.IOException;

public abstract class Command {
    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
