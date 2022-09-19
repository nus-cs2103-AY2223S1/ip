package duke.command;

import java.util.Arrays;

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

        int tagsIndex = this.description.indexOf("#");
        String todoDescription = tagsIndex < 0
                ? this.description
                : this.description.substring(0, tagsIndex).trim();
        String[] tags;

        if (tagsIndex < 0 || tagsIndex >= this.description.length() - 1) {
            tags = new String[0];
        } else {
            tags = Arrays.stream(this.description.substring(tagsIndex + 1).split(","))
                    .map(String::trim)
                    .filter(tag -> !tag.isEmpty())
                    .toArray(String[]::new);
        }

        Task newTask = new ToDo(todoDescription, tags);
        taskList.addTask(newTask);
        storage.saveTasksInStorage(taskList.toStorageRepresentation());

        String responseMessage = "This task is successfully added:\n " + newTask
                + "\nNow you have " + taskList.getTaskListSize() + " task(s) in the list";
        ui.printMessage(responseMessage);

        return new Pair<>(true, responseMessage);
    }
}
