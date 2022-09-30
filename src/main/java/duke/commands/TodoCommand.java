package duke.commands;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tasks.Todo;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

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
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        taskList.addTask(todo);
        storage.appendToFile(todo);
        return GuiText.formatAddTaskString(taskList.getSize() - 1, todo);
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
