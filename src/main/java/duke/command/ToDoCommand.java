package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * ToDoCommand is the command to add a ToDo to the TaskList.
 */
public class ToDoCommand extends Command {
    private final ToDo todo;

    /**
     * Constructor for ToDoCommand
     *
     * @param description Description of the task.
     */
    public ToDoCommand(String description) {
        super();
        this.todo = new ToDo(description);
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     */
    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.todo);
        ui.showAdd(this.todo, list.getSize());
        storage.saveList(list.save());
    }
}
