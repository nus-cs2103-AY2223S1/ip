package duke;

import java.io.IOException;

/**
 * Represents a command to exit the ChatBot.
 */
public class ExitCommand extends Command {

    ExitCommand() {

    }

    /**
     * Execute the exit command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return boolean true (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        return true;
    }

}
