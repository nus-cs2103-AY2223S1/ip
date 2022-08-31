package duke.command;

import duke.InvalidIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand marks a task as done.
 */
public class MarkCommand extends Command {
    private int indexToMark;

    /**
     * Constructor for MarkCommand.
     * @param indexToMark index of task to mark as done.
     */
    public MarkCommand(int indexToMark) {
        super();
        this.indexToMark = indexToMark;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        if (indexToMark < 0 || indexToMark > tasks.getSize()) {
            throw new InvalidIndexException();
        }
        tasks.getTask(indexToMark).markAsDone();
        storage.save(tasks.getTasks());
        return String.format("Nice! I've marked this task as done:\n" +
                "%s", tasks.getTask(indexToMark).toString());
    }
}
