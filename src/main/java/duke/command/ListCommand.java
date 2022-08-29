package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * List command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ListCommand implements Command {
    /**
     * Constructs a new instance of ListCommand.
     */
    public ListCommand() {}

    /**
     * Executes the ListCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the ListCommand.
     * @param taskList the task list used by the ListCommand.
     * @return the response pair.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList) {
        String responseMessage = taskList.isEmpty()
                ? "You have no tasks at the moment."
                : "Here are the tasks in your list\n" + taskList;
        ui.printMessage(responseMessage);
        return new Pair<>(true, responseMessage);
    }
}
