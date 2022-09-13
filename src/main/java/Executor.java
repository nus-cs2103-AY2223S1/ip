import java.io.IOException;

public abstract class Executor extends Command {

    protected String description;

    public Executor(String input) {
        description = input.substring(input.indexOf(' ') + 1);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ui.printLine(tasks.getSizeToString());
    }
}
