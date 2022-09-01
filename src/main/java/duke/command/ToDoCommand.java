package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * ToDoCommand is the command to add a ToDo to the TaskList.
 */
public class ToDoCommand extends Command {
    private final ToDo toDo;

    /**
     * Constructor for ToDoCommand
     *
     * @param description Description of the task.
     */
    public ToDoCommand(String description) {
        this.toDo = new ToDo(description);
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.toDo);
        storage.saveList(list.save());
        return ui.showAdd(this.toDo, list.getSize());
    }
}
