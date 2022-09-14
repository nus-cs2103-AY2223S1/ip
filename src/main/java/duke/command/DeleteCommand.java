package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

/**
 * DeleteCommand is a command that deletes the task.
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Constructor of Delete Command
     * @param deleteIndex
     */
    public DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    /**
     * Deletes a task, updates and saves the new tasks and returns delete message.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return delete task message
     * @throws InvalidInputException
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (deleteIndex > tasks.getSize() || deleteIndex < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        String task = tasks.deleteTask(this.deleteIndex);
        storage.save(tasks.getTaskListInString());
        return ui.printDelete(task, tasks.getSize());
    }
}
