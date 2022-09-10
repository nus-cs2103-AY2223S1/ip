package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundException;
import duke.task.TaskList;
/**
 * The BatchDescDeleteCommand class deletes Task with specified description.
 */
public class BatchDescDeleteCommand extends Command {
    private String description;

    /**
     * Constructor for a BatchDescDeleteCommand.
     *
     * @param description Description specified
     */
    public BatchDescDeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.batchDescDelete(description);
            String message = ui.printList(taskList.toString()) + '\n';
            message += ui.printSizeOfList(taskList.size());
            storage.save(taskList);
            return message;
        } catch (DukeOutOfBoundException e) {
            return ui.printErr(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
