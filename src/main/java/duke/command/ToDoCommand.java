package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

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
     * Adds Todo task to the task list and
     * return message that task was added.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The string representation of message of task being added.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        ToDo toDo = new ToDo(description);
        commandHistory.addCommand(this);
        taskList.add(toDo, storage);
        String message = "Nice! This task has been successfully added!";
        return ui.displayCommandMessage(message, toDo, taskList.getSize());
    }

    /**
     * Removes the ToDo task that has just been added is removed.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return Message that ToDo task has been removed.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        ToDo deletedToDo = new ToDo(this.description);
        taskList.remove(taskList.getSize() - 1, storage);
        String message = "This ToDo is no longer added!";
        return ui.displayCommandMessage(message, deletedToDo, taskList.getSize());
    }
}
