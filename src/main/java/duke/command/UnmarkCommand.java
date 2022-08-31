package duke.command;

import duke.InvalidIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand unmakrs a task as done.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    /**
     * Constructor for UnmarkCommand
     * @param indexToUnmark index of task to unmark.
     */
    public UnmarkCommand(int indexToUnmark) {
        super();
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        if (indexToUnmark < 0 || indexToUnmark > tasks.getSize()) {
            throw new InvalidIndexException();
        }
        tasks.getTask(indexToUnmark).markAsUndone();
        storage.save(tasks.getTasks());
        return String.format("OK, I've marked this task as not done yet:\n" +
                "%s", tasks.getTask(indexToUnmark).toString());
    }
}
