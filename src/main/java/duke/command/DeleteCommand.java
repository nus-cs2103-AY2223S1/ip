package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task deletion message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.deleteTask(index);
    }
}
