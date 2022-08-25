package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {

    private String details;

    public AddDeadlineCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!details.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The deadline is required. (/by)");
        }
        String[] split = details.split("/");
        String desc = split[0];
        String by = split[1].split(" ", 2)[1];
        Deadline deadline = new Deadline(desc, by);
        tasks.add(deadline);
    }
}
