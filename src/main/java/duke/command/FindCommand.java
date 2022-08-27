package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * FindCommand is a Command that searches for Tasks using a keyword.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the response from Duke after finding the Tasks containing the given keyword.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTasks(keyword);
    }
}
