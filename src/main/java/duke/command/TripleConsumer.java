package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public interface TripleConsumer {
    /**
     * Interface for our command.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    void execute(TaskList tasks, Ui ui, Storage storage);
}
