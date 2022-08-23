package duke.command;

import duke.exception.DukeException;
import duke.exception.ToDoException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

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
     * Executes the ToDoCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the ToDoCommand.
     * @param taskList the task list used by the ToDoCommand.
     * @throws DukeException If Duke fails to execute the ToDoCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        if (this.description.isEmpty()) {
            throw new ToDoException();
        }

        Task newTask = new ToDo(this.description);
        taskList.addTask(newTask);
        ui.printTaskCreationSuccessMessage(newTask,
                taskList.getTaskListSize());
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
    }
}
