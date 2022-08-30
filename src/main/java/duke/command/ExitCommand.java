package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Command that tells the App the terminate
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try { // saves after bye-command
            storage.save(tasks);
            return "EXITED";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
