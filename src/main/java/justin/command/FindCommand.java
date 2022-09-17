package justin.command;

import justin.Storage;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command that finds all the
 * matching tasks in the TaskList.
 * @author Justin Cheng.
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constructor for the FindCommand class.
     * @param description The description to find
     *                    matching tasks.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by listing out the tasks that
     * match the description provided.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message from the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.getFindMessage(list, description);
    }
}