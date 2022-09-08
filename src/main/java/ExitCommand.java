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
     * @param ui.
     * @return the response of the duke.
     */
    String execute(TaskList tasks, Ui ui) {
        String response = ui.exit();
        return response;
    }

}
