package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Duke as requested...";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exits Duke.\n"
            + "\tEx.: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) {
        ui.showWithCurrentColour(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }
}
