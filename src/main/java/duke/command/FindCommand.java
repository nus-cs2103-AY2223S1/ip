package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates an find command.
     *
     * @param keyword keyword used to search through <code>TaskList</code>
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task finding message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.find(keyword);
    }
}
