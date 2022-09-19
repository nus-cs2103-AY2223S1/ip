package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
import duke.Task;
import duke.ToDo;

/**
 * AddToDoCommand class to represent an instruction to add a new ToDo task to the TaskList.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class AddToDoCommand extends Command {
    private String description;
    private boolean isDone;

    /**
     * Constructor for AddToDoCommand class
     *
     * @param description String
     * @param isDone boolean
     */

    public AddToDoCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task todo = new ToDo(this.description, this.isDone);
        taskList.addTask(todo);
        storage.saveData(taskList);
        if (!storage.checkIsLoadingFile()) {
            UI.added(todo);
            response = UI.addedResponse(todo);
        }
    }

}
