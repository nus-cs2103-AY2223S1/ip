package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Bye command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ByeCommand implements Command {
    /**
     * Constructs a new instance of ByeCommand.
     */
    public ByeCommand() {}

    /**
     * Executes the ByeCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the ByeCommand.
     * @param taskList the task list used by the ByeCommand.
     * @return the response pair.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printFarewellMessage();
        return new Pair<>(false, "Bye!");
    }
}
