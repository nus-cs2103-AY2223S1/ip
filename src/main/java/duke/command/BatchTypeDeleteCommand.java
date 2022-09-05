package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundException;
import duke.task.TaskList;
/**
 * The BatchTypeDeleteCommand class deletes Task of specified Type from TaskList.
 */
public class BatchTypeDeleteCommand extends Command {
    private String taskType;

    /**
     * Constructor for a BatchTypeDeleteCommand.
     *
     * @param taskType Type of task specified
     */
    public BatchTypeDeleteCommand(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.batchTypeDelete(taskType);
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
