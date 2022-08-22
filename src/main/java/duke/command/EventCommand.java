package duke.command;

import duke.exception.DukeException;
import duke.exception.EventException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * Executes the EventCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the EventCommand.
     * @param taskList the task list used by the EventCommand.
     * @throws DukeException If Duke fails to execute the EventCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        List<String> eventInfo = Arrays.stream(this.description.split("/at", 2))
                .map(String::trim)
                .filter(info -> !info.isEmpty())
                .collect(Collectors.toList());

        if (eventInfo.size() < 2) {
            throw new EventException();
        }

        Task newTask = new Event(eventInfo.get(0), eventInfo.get(1));
        taskList.addTask(newTask);
        ui.printTaskCreationSuccessMessage(newTask, taskList.getTaskListSize());
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
    }
}
