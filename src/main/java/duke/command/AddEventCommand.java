package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add Events. Command contains the description and when event occurs.
 */
public class AddEventCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructor for AddEventCommand
     * @param description String describing the task
     * @param by String describing when task happens
     */
    public AddEventCommand(String description, String by) {
        this.description = description;
        this.by = by;
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
        Task task = new Events(description, by);
        tasks.add(task);
        ui.addSuccess(task, tasks);
        storage.save(tasks);
    }
}
