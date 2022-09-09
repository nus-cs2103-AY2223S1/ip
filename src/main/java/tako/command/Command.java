package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TakoException;
import tako.TaskList;
import tako.Ui;

/**
 * A command understood by Tako.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     * @throws IOException If an error occurs with storage.
     * @throws TakoException If an error occurs with tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TakoException;

    /**
     * Checks if Tako can exit after this command.
     *
     * @return Boolean for whether Tako can exit.
     */
    public abstract boolean isExit();
}
