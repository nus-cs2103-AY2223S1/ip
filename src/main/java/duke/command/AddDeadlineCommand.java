package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of adding a deadline task into the task list.
 */
public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND = "DEADLINE";

    public static final String MESSAGE_USAGE = COMMAND
            + "\nAdds an Deadline task with date and time, deadline <description> /by <DateTime>"
            + "\nExample: deadline wash clothes /by Dec 21 2022 1030";

    /**
     * Constructs an AddDeadlineCommand instance.
     *
     * @param task deadline task
     */
    public AddDeadlineCommand(Deadline task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        ui.showAddTask(super.getTask(), tasks.getNumOfRemainingTasks());
    }
}
