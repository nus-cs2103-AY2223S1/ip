package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * Represents a ByeCommand object to be called when user inputs 'bye'.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes bye command.
     *
     * @param tasks
     * @param ui displays goodbye message.
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.showGoodbye();
    }
}
