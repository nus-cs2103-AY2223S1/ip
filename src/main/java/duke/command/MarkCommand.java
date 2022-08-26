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

    public void execute(TaskList tasks) throws DukeException {
        tasks.markTask(pos, bool);
    }
}
