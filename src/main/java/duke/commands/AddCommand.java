package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Represents a command that adds a task to the task list.
 * The given task-to-add may be a deadline, an event, or a to-do.
 */
public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this duke.task:\n ";
    private Task taskToAdd;

    /**
     * Constructs a new AddCommand instance.
     *
     * @param taskToAdd Task to be added to the task list.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the AddCommand by storing the task to the given task list.
     * Writes the result to the local disk.
     *
     * @param tasks Task List that stores tasks.
     * @param storage Storage in charge of writing to the local disk.
     * @return A string showing a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            checkDuplicates(tasks, taskToAdd);
            tasks.add(taskToAdd);
            String successMessage = MESSAGE_SUCCESS + taskToAdd.toString()
                    + "\n" + tasks.getCount();
            storage.appendToFile(taskToAdd.toString());
            return successMessage;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the given task has already been stored in the specified task list.
     *
     * @param taskList Given task list.
     * @param task Given task.
     * @throws DukeException If the task is already in the task list.
     */
    public void checkDuplicates(TaskList taskList, Task task) throws DukeException {
        if (taskList.hasDuplicates(task)) {
            String errorMessage = "☹ Don't you realise the input task has already been stored in the task list?";
            throw new DukeException(errorMessage);
        }
    }

}
