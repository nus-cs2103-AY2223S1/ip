package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
    private String details;
    private String time;

    public DeadlineCommand(String details, String time) {
        this.details = details;
        this.time = time;
    }

    @Override
    public void execute(TaskList list) throws DukeException {
        Deadline task = new Deadline(details, time);
        list.add(task);
        Ui.added(task);
    }
}
