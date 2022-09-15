package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

/**
 * UpdateDateCommand is a command that updates a task date.
 *
 * @author Eugene Tan
 */
public class UpdateDateCommand extends Command {

    private int updateIndex;
    private LocalDate updatedDate;

    /**
     * Constructor for UpdateDateCommand.
     * @param updateIndex the index of the task to update
     * @param updatedDate the updated date
     */
    public UpdateDateCommand(int updateIndex, LocalDate updatedDate) {
        super();
        this.updateIndex = updateIndex;
        this.updatedDate = updatedDate;
    }

    /**
     * Updates a task with a new given date.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return Updated task String representation and message
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (updateIndex > tasks.getSize() || updateIndex <= 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        String updatedTask = tasks.updateTaskDate(this.updateIndex, this.updatedDate);
        storage.save(tasks.getTaskListInString());
        return ui.printUpdate(updatedTask);
    }



}
