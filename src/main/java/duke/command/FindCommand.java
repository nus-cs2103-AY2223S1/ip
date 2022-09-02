package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class FindCommand extends Command {
    String keyword;

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
