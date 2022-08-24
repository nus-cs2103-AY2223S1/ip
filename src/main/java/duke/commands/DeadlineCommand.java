package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private Deadline d;

    public DeadlineCommand(Deadline d) {
        this.d = d;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(d);
    }
}
