package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command used to exit the session with the chatBot.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    /**
     * Ends the session with the user, printing out
     *     the corresponding message to the user and saving data to the file.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     */
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        ui.printOutro();
    }
}
