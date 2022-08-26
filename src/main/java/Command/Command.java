/**
 * Abstract class that is the parent class of all Commands
 */
public abstract class Command {
    private boolean exit;

    public Command() {
        this.exit = false;
    }

    public abstract void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.exit;
    }
}