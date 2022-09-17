package duke.command;
import duke.Main;
import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * This class represents the exit command that allow the user
 * to quit Duke.
 */
public class ExitCommand extends Command{

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Main.EXIT_SIGNAL;
    }

    @Override 
    public boolean isExit() {
        return true;
    }
}
