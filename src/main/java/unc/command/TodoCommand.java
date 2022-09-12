package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.task.Todo;


/**
 * Command that adds a new Todo to list when executed.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructor.
     *
     * @param input Description part of input following "todo".
     */
    public TodoCommand(String input) {
        assert input != null : "A todo task shouldn't be created with null description.";
        this.description = input;
    }

    /**
     * Adds a new Todo to list.
     * Saves updated list.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        storage.save(taskList);
        return ui.addTodo(taskList, newTodo);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
