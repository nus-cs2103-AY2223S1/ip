package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.data.task.ToDo;
import stashy.storage.Storage;
import stashy.ui.Ui;

public class AddTodoCommand extends Command {
    public static final String KEYWORD = "todo";
    private ToDo todo;

    /**
     * Constructor method.
     *
     * @param todo The todo to be added
     */
    public AddTodoCommand(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a Todo task class to the task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        tasks.add(this.todo);
        ui.showIndented("There, we have a new todo:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.");
    }
}
