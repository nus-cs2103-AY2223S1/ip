package duke.command;


import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.TaskMarkException;
import duke.exception.TaskNotFoundException;

import duke.task.Task;

/**
 * Represents a MarkCommand object to be called when user inputs 'mark'.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String TASK_ADD = "Good Job! I will mark this task as done: ";
    public int index;

    /**
     * Constructs MarkCommand with index to be marked.
     *
     * @param index index to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes MarkCommand by marking the task specified by the index as done.
     *
     * @param tasks list of task where the index specified will be marked as done.
     * @param ui display the task that was marked done.
     * @param storage updates the storage when task is marked done.
     * @throws TaskMarkException when task specified by the index is already marked done.
     * @throws TaskNotFoundException when index given is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMarkException, TaskNotFoundException {
        Task task = tasks.markTask(index);
        storage.update(tasks);
        displayCommand(ui, TASK_ADD, task, tasks.getStatus());
    }
}
