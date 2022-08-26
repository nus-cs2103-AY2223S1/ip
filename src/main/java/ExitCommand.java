/**
 * Represents command to Duke to exit the application.
 *
 * @author WR3nd3
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        ui.showGoodbye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
