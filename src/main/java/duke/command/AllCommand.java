package duke.command;

import java.util.Arrays;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * All Command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class AllCommand implements Command {
    private final String[] tags;

    /**
     * Constructs a new AllCommand instance based on tags.
     *
     * @param chosenTags the chosen tags.
     * @throws DukeException If the chosen tags are empty.
     */
    public AllCommand(String chosenTags) throws DukeException {
        if (chosenTags == null || chosenTags == "") {
            throw new DukeException("The tags after any command "
                    + "must be a non-empty string!");
        }

        // Filter out all empty tags
        this.tags = Arrays.stream(chosenTags.split(","))
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toArray(String[]::new);
    }

    /**
     * Executes the AllCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage to be used by the AllCommand.
     * @param taskList the task list to be used by the AllCommand.
     * @return the response pair.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList) {
        TaskList filteredTasks = taskList.filterByAllTags(this.tags);

        String responseMessage = filteredTasks.isEmpty()
                ? "There are no task that match all the tags"
                : "Here are the tasks in your list that match all the tags\n"
                + filteredTasks;
        ui.printMessage(responseMessage);

        return new Pair<>(true, responseMessage);
    }
}
