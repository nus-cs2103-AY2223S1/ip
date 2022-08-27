package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exits Duke.\n"
            + "\tEx.: " + COMMAND_WORD;

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        return new CommandResult("Exiting Duke as requested...");
    }
}
