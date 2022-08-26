package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The FindCommand class represents a command that
 * finds a task in Duke's task list using a keyword.
 */
public class FindCommand extends Command {
    /** The keyword to search the task list with. */
    private final String keyword;

    /**
     * Constructs a new FindCommand.
     *
     * @param keyword The specified keyword to search the task list with.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingList = tasks.allContaining(keyword);
        ui.showFound(matchingList);
    }
}
