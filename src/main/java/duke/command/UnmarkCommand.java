package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public String run() throws DukeException {
        String reply;
        try {
            reply = this.tasks.unmarkTask(Integer.parseInt(content) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number need to be an integer!");
        }
        return reply;
    }
}