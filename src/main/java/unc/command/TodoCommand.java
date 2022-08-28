package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.UncException;
import unc.task.Todo;
import unc.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        ui.addToDo(taskList, newTodo);
        storage.save(taskList);
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
