package duke.commands;

import duke.entities.Todo;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Adds Todo to the tasklist
 */
public class AddTodoCommand extends ShowList {
    protected String description;
    protected String instruction;

    /**
     * Adds a Todo to the tasklist
     * @param list of tasks
     * @param description of task
     * @param input that generated the task
     */
    public AddTodoCommand(TaskList list, String description, String input) {
        super(list);
        this.description = description;
        this.instruction = input;
    }

    /**
     * Add new todo to the task list
     *
     * @return wrapped message
     * @throws DukeException when something goes wrong
     */
    @Override
    public String execute() throws DukeException {
        Todo currentTodo = new Todo(description);
        tasks.addTask(currentTodo);
        return wrapWithoutLines(Messages.ADD_TODO.toString(), currentTodo.toString());
    }
}
