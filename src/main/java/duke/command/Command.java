package duke.command;

import java.util.function.Consumer;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param storage The {@code Storage} to use.
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    public abstract void execute(Storage storage, Consumer<String> printer, TaskList tasks);
}


