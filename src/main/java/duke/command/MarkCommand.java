package duke.command;

import duke.Duke;
import duke.exceptions.DukeBadFormatException;
import duke.exceptions.DukeMissingParameterException;
import duke.storage.Storage;
import duke.exceptions.DukeIndexRangeException;
import duke.task.TaskList;

import java.lang.reflect.Array;

/**
 * Represents the command for marking a task in Duke's TaskList.
 */
public class MarkCommand implements Command{
    private final int to_mark;

    /**
     * Constructs a MarkCommand.
     *
     * @param inputs Array of command parsed by parser.
     */
    public MarkCommand(String[] inputs) {
        try {
            this.to_mark = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeBadFormatException("mark <integer>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingParameterException("mark <integer>", "target to mark");
        }
    }

    /**
     * Executes the mark command by marking the task and printing the mark message.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     * @throws DukeIndexRangeException Exception when target to mark does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeIndexRangeException {
        String res = tasks.mark(to_mark);
        storage.refresh(tasks);
        return res;
    }
}
