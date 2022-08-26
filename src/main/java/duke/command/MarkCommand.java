package duke.command;

import duke.DukeException;
import duke.TaskList;

public class MarkCommand extends Command {
    private boolean bool;
    private int pos;
    
    public MarkCommand(boolean bool, int pos) {
        this.bool = bool;
        this.pos = pos;
    }

    /**
     * Executes the command by marking the item at pos in list.
     *
     * @param tasks The user's current list of tasks.
     * 
     * @throws DukeException If the data file cannot be accessed.
     */
    public void execute(TaskList tasks) throws DukeException {
        tasks.markTask(pos, bool);
    }
}
