package duke.command;

import duke.DukeException;
import duke.TaskList;

public class DeleteCommand extends Command {
    private int pos;
    
    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Executes the command by deleting the item at pos in list.
     *
     * @param tasks The user's current list of tasks.
     * 
     * @throws DukeException If the data file cannot be accessed.
     */
    public void execute(TaskList tasks) throws DukeException {
        tasks.deleteTask(pos);
    }
}
