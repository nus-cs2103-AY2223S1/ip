package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public String run() throws DukeException {
        String[] splitMessage = this.content.split(" /by ", 2);
        if (splitMessage.length < 2) {
            throw new DukeException("You forgot to add the deadline!");
        }
        return this.tasks.addTask(new Deadline(splitMessage[0], splitMessage[1]));
    }
}