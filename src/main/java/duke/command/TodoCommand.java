package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that is used to add a Todo Task.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for TodoCommand.
     * @param description Description of the Todo Task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a Todo Task to list and returns the toString of the Task added.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of the Task added.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        Task task = list.addTodo(description);
        storage.writeToFile(list);
        return Ui.addTask(task);
    }
}
