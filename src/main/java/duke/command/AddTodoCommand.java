package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddTodoCommand extends AddCommand {

    public static final String COMMAND = "TODO";

    public static final String MESSAGE_USAGE = COMMAND
                + "\n   Adds a Todo task, todo <description>"
                + "\n   Example: todo wash clothes ";

    /**
     * Constructs an AddCommand instance
     *
     * @param taskToAdd Task instance that needs to be added.
     */
    public AddTodoCommand(Task taskToAdd) {
        super(taskToAdd);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        ui.showAddTask(super.getTask(), tasks.getNumOfRemainingTasks());
    }
}
