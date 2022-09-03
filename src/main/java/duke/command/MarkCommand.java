package duke.command;

import duke.DukeException;
import duke.TaskList;

public class MarkCommand extends Command {
    private boolean isDone;
    private int pos;
    
    public MarkCommand(boolean isDone, int pos) {
        this.isDone = isDone;
        this.pos = pos;
    }

    /**
     * Executes the command by marking the item at pos in list.
     *
     * @param tasks The user's current list of tasks.
     * 
     * @throws DukeException If the data file cannot be accessed.
     */
    public String execute(TaskList tasks) throws DukeException {
        return tasks.markTask(pos, isDone);
    }
}
