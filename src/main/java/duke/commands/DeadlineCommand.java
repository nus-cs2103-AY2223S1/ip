package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDateException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to add this task: \n";

    private Deadline deadline;

    public DeadlineCommand(String description, String by) throws DukeInvalidDateException {
        super();
        deadline = new Deadline(description, by);
    }

    public DeadlineCommand(String description, String by, boolean isMarked) throws DukeInvalidDateException {
        super();
        deadline = new Deadline(description, by);
        deadline.markAsDone();
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(deadline);
        ui.showMessage(MESSAGE_SUCCESS + deadline + " " + tasks.showNumberOfTasks());
    }
}
