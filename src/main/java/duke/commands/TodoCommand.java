package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Todo;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class tells Duke to add a new to-do task with the specified description.
 */
public class TodoCommand implements Command {

    /** The to-do task to add. */
    private Todo todo;

    /**
     * Constructs a TodoCommand object.
     *
     * @param desc The description of the to-do task.
     */
    public TodoCommand(String desc) {
        todo = new Todo(desc);
    }

    /**
     * Executes the to-do command from the user.
     *
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(todo);
            storage.appendToFile(todo);
            ui.sayAddTask(todo);
            ui.sayTaskListSize(taskList);
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof TodoCommand) {
            TodoCommand that = (TodoCommand) o;
            if (todo.equals(that.todo)) {
                return true;
            }
        }
        return false;
    }
}
