package duke.commands;

import duke.entities.Todo;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Adds Todo to the tasklist
 */
public class AddTodoCommand extends ShowList {
    protected String descrition;
    protected String instruction;

    /**
     * Adds a Todo to the tasklist
     * @param list of tasks
     * @param description of task
     * @param input that generated the task
     */
    public AddTodoCommand(TaskList list, String description, String input) {
        super(list);
        this.descrition = description;
        this.instruction = input;
    }

    /**
     * Add new todo to the task list
     * @throws DukeException when something goes wrong
     */
    @Override
    public void execute() throws DukeException {
        Todo currentTodo = new Todo(descrition);
        tasks.addTask(currentTodo);
        wrapWithLines(Messages.ADD_TODO.toString(), currentTodo.toString());
    }
}
