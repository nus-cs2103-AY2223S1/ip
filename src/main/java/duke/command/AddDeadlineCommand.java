package duke.command;

import java.time.LocalDateTime;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add deadlines. Command contains the description and when task is due.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Constructor for AddDeadlineCommand
     * @param description String describing the task
     * @param by LocalDateTime object describing when task is due
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
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
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.addSuccess(task, tasks);
        storage.save(tasks);
    }
}
