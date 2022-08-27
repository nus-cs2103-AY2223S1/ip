package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/*
 * Encapsulates a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a MarkCommand.
     * 
     * @param index Task ID of task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand to mark a Task as done.
     * 
     * @param tasks TaskList containing task to be marked as done.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String doneTask = tasks.markAsDone(index);
        storage.save(tasks);
        ui.printString("Task marked as done:\n" + doneTask);
    }

    /**
     * Computes equality of two MarkCommands.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }
        MarkCommand otherCommand = (MarkCommand) o;
        return this.index == otherCommand.index;
    }
}
