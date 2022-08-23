package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private Deadline d;

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(Deadline d) {
        this.d = d;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(d);
    }
}
