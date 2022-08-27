package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

/**
 * ToDoCommand class represents the ToDo command given by the user.
 */
public class ToDoCommand extends Command {
    private final String description;

    /**
     * Constructor of the ToDoCommand class.
     * Sets the description of the task to the local variable.
     *
     * @param description The description of the task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds Todo task to the task list.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        ToDo toDo = new ToDo(description);
        taskList.add(toDo, storage);
        String message = "Nice! This task has been successfully added!";
        ui.displayCommandMessage(message, toDo, taskList.getSize());
        ui.printBorder();
    }
}
