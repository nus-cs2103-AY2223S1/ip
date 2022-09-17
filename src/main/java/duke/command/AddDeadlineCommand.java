package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND = "DEADLINE";

    public static final String MESSAGE_USAGE = COMMAND
            + "\n   Adds an Deadline task with date and time, deadline <description> /by <DateTime>"
            + "\n   Example: deadline wash clothes /by Dec 21 2022 1030";

    /**
     * Constructs an AddCommand instance
     *
     * @param taskToAdd Task instance that needs to be added.
     */
    public AddDeadlineCommand(Task taskToAdd) {
        super(taskToAdd);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        ui.showAddTask(super.getTask(), tasks.getNumOfRemainingTasks());
    }
}