package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * Represents a command to add ToDos. Command contains the description.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructor for TodoCommand
     * @param description String describing the task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command
     * @param tasks TaskList object that stores tasks
     * @param ui Ui object deals with user interaction
     * @param storage Storage object that handles text file
     * @throws DukeException exception thrown in TaskList, Ui or Storage methods
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDos(description);
        tasks.add(task);
        ui.addSuccess(task, tasks);
        storage.save(tasks);
    }
}
