package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find a list of tasks given a user input
 */
public class FindCommand extends Command {

    private String description;

    /**
     * Constructor for {@code FindCommand}
     * @param description the searching criteria
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * To execute the {@code FindCommand}
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.findTask(tasks, description);
    }
}
