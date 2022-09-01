package uwu.command;

import uwu.exception.NullTaskException;
import uwu.exception.UwuException;

import uwu.Storage;

import uwu.task.Task;
import uwu.task.TaskList;

import uwu.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    private int index;

    /** The user command parsed into DeleteCommand. */
    private String userCommand;

    /**
     * Constructor for a DeleteCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public DeleteCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.index = Integer.parseInt(taskData[1].trim()) - 1;
    }

    /**
     * Executes the DeleteCommand which deletes a task of the specified index
     * from the stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task index is out of bounds.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (index >= tasks.size()) {
            throw new NullTaskException("\thm...it seems that task " + String.valueOf(index + 1) + " does not exist ><"
                    + "\n\tplease check that you have keyed in the right task number~ <:");
        }
        Task task = tasks.remove(index);
        storage.save(tasks.taskListToStorageString());
        ui.deleteTask(task, tasks.size());
    }

    /**
     * Returns whether DeleteCommand exits the program.
     *
     * @return false as DeleteCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
