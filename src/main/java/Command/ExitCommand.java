import java.io.IOException;

/**
 * Adds to the tasklist
 */
public class ExitCommand extends Command {

    boolean exit;

    public ExitCommand() {
        this.exit = true;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            storage.save();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        ui.endMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
