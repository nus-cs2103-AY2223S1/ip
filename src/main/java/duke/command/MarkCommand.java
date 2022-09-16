package duke.command;

import duke.storage.Storage;
import duke.exceptions.DukeInvalidParameterException;
import duke.task.TaskList;

/**
 * Represents the command for marking a task in Duke's TaskList.
 */
public class MarkCommand implements Command{
    private final int TO_MARK;

    /**
     * Constructs a MarkCommand.
     *
     * @param TO_MARK Index of the task to be marked in Duke's TaskList.
     */
    public MarkCommand(int TO_MARK) {
        this.TO_MARK = TO_MARK;
    }

    /**
     * Executes the mark command by marking the task and printing the mark message.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeInvalidParameterException {
        String res;
        try {
            res = tasks.mark(TO_MARK);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidParameterException("target to mark does not exist!");
        }
        storage.refresh(tasks);

        return res;
    }
}
