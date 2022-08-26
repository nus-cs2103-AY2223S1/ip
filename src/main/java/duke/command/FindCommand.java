package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents find command
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs find command
     *
     * @param keyword Keyword to find tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks matching keyword
     *
     * @param tasks Task list
     * @param ui User's Interface
     * @param storage Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.findTask(tasks.find(keyword));
    }
}
