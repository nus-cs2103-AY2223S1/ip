package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The FindCommand class represents the find command to find task with matching keyword from Duke's task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initializes an instance of FindCommand.
     *
     * @param keyword The keyword to filter through the task list.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        return Command.WRAPPER.getFindResponse(tasklist.getTaskListWithKeyword(keyword).toString());
    }
}
