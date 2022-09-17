package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends AddCommand {

    public static final String COMMAND = "EVENT";

    public static final String MESSAGE_USAGE = COMMAND
            + "\n   Adds an Event task with date and time, event <description> /at <DateTime>"
            + "\n   Example: event wash clothes /at Dec 21 2022 1030";

    /**
     * Constructs an AddCommand instance
     *
     * @param taskToAdd Task instance that needs to be added.
     */
    public AddEventCommand(Task taskToAdd) {
        super(taskToAdd);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        ui.showAddTask(super.getTask(), tasks.getNumOfRemainingTasks());
    }
}
