package duke.command;

import duke.exceptions.DukeBadFormatException;
import duke.exceptions.DukeMissingParameterException;
import duke.storage.Storage;
import duke.exceptions.DukeIndexRangeException;
import duke.task.TaskList;

/**
 * Represents the command for the deleting tasks from Duke's TaskList.
 */
public class DeleteCommand implements Command{

    private final int to_delete;

    /**
     * Constructs a DeleteCommand.
     *
     * @param inputs Array of command parsed by parser.
     */
    public DeleteCommand(String[] inputs) {
        try {
            this.to_delete = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeBadFormatException("delete <integer>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingParameterException("delete <integer>", "target to delete");
        }
    }

    /**
     * Executes the delete command and prints the results of this delete command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param storage Storage created when starting Duke.
     * @throws DukeIndexRangeException Exception when target to mark does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeIndexRangeException {
        String res = tasks.delete(to_delete);
        storage.refresh(tasks);
        return res;
    }
}
