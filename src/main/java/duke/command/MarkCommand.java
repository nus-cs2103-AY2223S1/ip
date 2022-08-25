package duke.command;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(indexToMark).markAsDone();
        ui.showMarkMessage(tasks.getTask(indexToMark));
    }
}
