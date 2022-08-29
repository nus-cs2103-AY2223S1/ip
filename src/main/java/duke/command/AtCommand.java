package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * At command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class AtCommand implements Command {
    private LocalDate selectedDate;

    /**
     * Constructs a new AtCommand based on description.
     *
     * @param description the description string.
     * @throws DukeException If description cannot be parsed to a valid LocalDate object.
     */
    public AtCommand(String description) throws DukeException {
        try {
          this.selectedDate = LocalDate.parse(description);
        } catch (DateTimeParseException error) {
            throw new DukeException("The time given is not a valid date. "
                    + "Try to represent the time in yyyy-mm-dd format.");
        }
    }

    /**
     * Executes the AtCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage to be used by the AtCommand.
     * @param taskList the task list to be used by the AtCommand.
     * @return the response pair.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList) {
        TaskList filteredTasks = taskList.findByDate(this.selectedDate);
        String responseMessage = filteredTasks.isEmpty()
                ? "There are no task that match the keyword"
                : "Here are the tasks in your list that match the keyword\n"
                + filteredTasks;
        ui.printMessage(responseMessage);
        return new Pair<>(true, responseMessage);
    }

}
