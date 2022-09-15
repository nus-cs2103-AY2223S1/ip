package anya;

/**
 * Represents a command to exit the ChatBot.
 */
public class ExitCommand extends Command {

    ExitCommand() {

    }

    /**
     * Execute the exit command.
     * @param tasks current tasklist.
     * @param ui interaction class.
     * @return the response of the anya.
     */
    String execute(TaskList tasks, Ui ui) {
        String response = ui.exit();
        return response;
    }

}
