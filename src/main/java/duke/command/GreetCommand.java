package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Encapsulates the command of greeting the user.
 *
 * @author Sun Ruoxin
 */
public class GreetCommand extends Command {
    /**
     * The logo of the chatbot.
     */
    protected String logo = "     _   _    ______     _____ ____\n"
            + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
            + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
            + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
            + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    /**
     * Executes the command.
     *
     * @param tasks   the list of tasks
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return logo + " \n" + "Hello. I'm Jarvis.\n" + "What can I do for you?";
    }
}
