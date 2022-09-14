package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a HelpCommand that gives user a link to the documentation page.
 *
 * @author Elgin
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     * @return The message that Duke wants to say to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String documentationLink = "https://elginl/github.io/ip/";

        return "Visit this page for a detailed documentation: \n" + documentationLink;
    }
}
