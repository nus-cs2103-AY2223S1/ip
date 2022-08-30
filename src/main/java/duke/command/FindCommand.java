package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all current tasks.
 */
public class FindCommand extends Command{

    private String keyword;

    /**
     * Initialises the find command with the keyword to find.
     * @param keyword Keyword that matches task descriptions to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command.
     * @param tasks Task list to be listed.
     * @param ui UI to display list.
     * @param storage Storage used in application.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindResults(tasks, keyword);
    }

    /**
     * Checks if Duke application should exit after this command.
     * @return False as this command is not bye.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
