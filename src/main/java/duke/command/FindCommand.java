package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.NoKeywordException;
import duke.task.TaskList;

/**
 * A class to represent the find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";
    private String keyword;

    /**
     * Contructs a new FindCommand instance.
     *
     * @param keyword the search keyword.
     * @throws DukeException If no keyword is specified.
     */
    public FindCommand(String keyword) throws DukeException {
        if (keyword.equals("")) {
            throw new NoKeywordException();
        }
        this.keyword = keyword;
    }

    /**
     * Finds the specified tasks from the list of tasks
     *     and returns a response message.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) {
        TaskList filtered = taskList.filter(keyword);
        return Ui.getFilteredTasksMessage(filtered);
    }

}
