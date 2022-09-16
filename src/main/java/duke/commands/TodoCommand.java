package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * This class represents a command to add a todo to the task list
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructs a new Todo Command
     * @param description The description of the task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ToDo toDo = new ToDo(description);
        taskList.addToList(toDo);
        storage.save(taskList);
        return ui.printAddTask(toDo, taskList.getSize());
    }
}
