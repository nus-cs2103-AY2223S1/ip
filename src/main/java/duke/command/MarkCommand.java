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

    public void execute(TaskList tasks) throws DukeException {
        tasks.markTask(pos, true);
    }
}
