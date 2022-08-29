package duke.command;

import duke.exception.DukeException;
import duke.exception.ToDoException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * ToDo command for Duke application
 *
 * @author Farrel Dwireswara Salim
 */
public class ToDoCommand implements Command {
    private final String description;

    /**
     * Constructs a new instance of ToDoCommand.
     *
     * @param description the description of the ToDoCommand.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the ToDoCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the ToDoCommand.
     * @param taskList the task list used by the ToDoCommand.
     * @return the response pair.
     * @throws DukeException If Duke fails to execute the ToDoCommand.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        if (this.description.isEmpty()) {
            throw new ToDoException();
        }

        Task newTask = new ToDo(this.description);
        taskList.addTask(newTask);
        String responseMessage = "This task is successfully added:\n " + newTask
                + "\nNow you have " + taskList.getTaskListSize() + " task(s) in the list";
        ui.printMessage(responseMessage);
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
        return new Pair<>(true, responseMessage);
    }
}
