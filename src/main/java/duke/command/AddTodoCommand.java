package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of adding a todo task into the task list.
 */
public class AddTodoCommand extends AddCommand {

    public static final String COMMAND = "TODO";

    public static final String MESSAGE_USAGE = COMMAND
                + "\n   Adds a Todo task, todo <description>"
                + "\n   Example: todo wash clothes ";

    /**
     * Constructs an AddCommand instance.
     *
     * @param task Todo task
     */
    public AddTodoCommand(Todo task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        ui.showAddTask(super.getTask(), tasks.getNumOfRemainingTasks());
    }
}
