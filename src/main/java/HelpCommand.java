package anya;

/**
 * Represents a command to display all the commands format in this application.
 */
public class HelpCommand extends Command {

    HelpCommand() {

    }

    /**
     * Execute the help command.
     * @param tasks current tasklist.
     * @param ui the interaction class.
     * @return the response of Anya.
     */
    String execute(TaskList tasks, Ui ui) {
        String response = ui.getHelp();
        return response;
    }

}

