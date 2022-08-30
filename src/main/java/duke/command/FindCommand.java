package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents find command.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs find command.
     *
     * @param keyword Keyword to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks matching keyword.
     *
     * @param tasks Task list.
     * @param ui User's Interface.
     * @param storage Storage.
     * @return duke's response.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response = ui.findTask(tasks.find(keyword));
        return response;
    }
}
