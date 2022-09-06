package commands;

import data.ToDo;

/**
 * Command to create a todo.
 */
public class ToDoCommand extends NewTaskCommand {
    /**
     * Command to create a todo with title.
     *
     * @param title Title of task
     */
    public ToDoCommand(String title) {
        super(new ToDo(title, false));
    }
}
