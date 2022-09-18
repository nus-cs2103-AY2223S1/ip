package duke.command;

import duke.exceptions.DukeBadFormatException;
import duke.exceptions.DukeMissingParameterException;
import duke.storage.Storage;
import duke.exceptions.DukeIndexRangeException;
import duke.task.TaskList;

/**
 * Represents the command for un-marking a task in Duke's TaskList.
 */
public class UnmarkCommand implements Command{

    private final int to_unmark;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param inputs Array of command parsed by parser.
     */
    public UnmarkCommand(String[] inputs) {
        try {
            this.to_unmark = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeBadFormatException("unmark <integer>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingParameterException("unmark <integer>", "target to unmark");
        }
    }

    /**
     * Executes the un-mark command by un-marking the task and printing the un-mark message.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     * @throws DukeIndexRangeException Exception when target to mark does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeIndexRangeException {
        String res = tasks.unmark(to_unmark);
        storage.refresh(tasks);
        return res;
    }
}
