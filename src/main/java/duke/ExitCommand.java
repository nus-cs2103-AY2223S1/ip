package duke;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Returns exit message.
     *
     * @returns exit message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
