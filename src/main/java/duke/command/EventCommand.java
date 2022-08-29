package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.EventException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Event command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class EventCommand implements Command {
    private final String description;

    /**
     * Constructs a new instance of EventCommand.
     *
     * @param description the description of the EventCommand.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the EventCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the EventCommand.
     * @param taskList the task list used by the EventCommand.
     * @return the response pair.
     * @throws DukeException If Duke fails to execute the EventCommand.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        List<String> eventInfo = Arrays.stream(description.split("/at", 2))
                .map(String::trim)
                .filter(info -> !info.isEmpty())
                .collect(Collectors.toList());

        if (eventInfo.size() < 2) {
            throw new EventException();
        }

        Task newTask = new Event(eventInfo.get(0), eventInfo.get(1));
        taskList.addTask(newTask);

        String responseMessage = "This task is successfully added:\n " + newTask
                + "\nNow you have " + taskList.getTaskListSize() + " task(s) in the list";
        ui.printMessage(responseMessage);
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
        return new Pair<>(true, responseMessage);
    }
}
