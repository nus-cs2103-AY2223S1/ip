package duke;

import java.io.IOException;

/**
 * Represents a command to exit the ChatBot.
 */
public class ExitCommand extends Command {

    private static boolean isExit;

    ExitCommand() {
        this.isExit = true;
    }

    /**
     * Execute the exit command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.exit();
        return response;
    }

}
