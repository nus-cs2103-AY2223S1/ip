package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.data.task.ToDo;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to add a ToDo object.
 */
public class AddTodoCommand extends Command {
    public static final String KEYWORD = "todo";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nAdds a todo task."
        + "\n\nExample: todo buy book";
    private ToDo todo;
    private boolean showHelp;

    /**
     * Constructor method.
     *
     * @param todo The todo to be added
     * @param showHelp Whether to show help or not
     */
    private AddTodoCommand(ToDo todo, boolean showHelp) {
        this.todo = todo;
        this.showHelp = showHelp;
    }

    /**
     * Overloaded constructor method to add a todo.
     *
     * @param todo The todo to be added
     */
    public AddTodoCommand(ToDo todo) {
        this(todo, false);
    }

    /**
     * Overloaded constructor method to show help.
     */
    public AddTodoCommand() {
        this(null, true);
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
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.showHelp) {
            return HELP_MESSAGE;
        } else {
            tasks.add(this.todo);
            return "There, we have a new todo:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.";
        }
    }
}
