package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/*
 * Encapsulates a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand.
     * 
     * @param index Task ID of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand to delete a Task from the list.
     * 
     * @param tasks TaskList that task will be deleted from.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String deletedTask = tasks.delete(index);
        storage.save(tasks);
        ui.printString("I've removed this task:\n" + deletedTask);
    }

    /**
     * Computes equality of two DeleteCommands.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherCommand = (DeleteCommand) o;
        return this.index == otherCommand.index;
    }
}
