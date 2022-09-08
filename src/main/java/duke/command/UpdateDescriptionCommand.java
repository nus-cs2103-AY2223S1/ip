package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

/**
 * UpdateDescriptionCommand is a command that updates a task description.
 *
 * @author Eugene Tan
 */
public class UpdateDescriptionCommand extends Command {
    private int updateIndex;
    private String updatedDescription;

    /**
     * Constructor for UpdateDescriptionCommand.
     * @param updateIndex the index of the task to update
     * @param updatedDescription the updated description
     */
    public UpdateDescriptionCommand(int updateIndex, String updatedDescription) {
        super();
        this.updateIndex = updateIndex;
        this.updatedDescription = updatedDescription;
    }

    /**
     * Updates a task with a new given description.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return Updated task String representation and message
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (updateIndex > tasks.getSize() || updateIndex < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        String updatedTask = tasks.updateTaskDescription(this.updateIndex, this.updatedDescription);
        storage.save(tasks.getTaskListInString());
        return ui.printUpdate(updatedTask);
    }
}
