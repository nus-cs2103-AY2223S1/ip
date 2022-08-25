package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command that adds a task to the task list.
 * The given task-to-add may be a deadline, an event, or a to-do.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String COMMAND_WORD_TODO = "todo";
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
     * @param tasks Task List that stores the task.
     * @param ui Ui that shows message to the user.
     * @param storage Storage in charge of writing to the local disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(taskToAdd);
        String successMessage = MESSAGE_SUCCESS + taskToAdd.toString()
                + "\n" + tasks.getCount();
        ui.showSuccessMessage(successMessage);
        storage.appendToFile(taskToAdd.toString(), ui);
    }

    /**
     * Keeps the programme running.
     *
     * @return True.
     */
    @Override
    public boolean isRunning() {
        return true;
    }

}
