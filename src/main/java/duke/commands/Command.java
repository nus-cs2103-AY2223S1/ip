package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class encapsulates a set of instructions to be performed by Duke.
 */
public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
