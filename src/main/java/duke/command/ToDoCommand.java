package duke.command;

import duke.exception.DuplicateTaskException;
import duke.logic.TaskList;
import duke.task.ToDo;

/**
 * ToDoCommand is a command for Duke to remember a todo.
 *
 * @author totsukatomofumi
 */
public class ToDoCommand extends Command {
    /** Task list the command has to add a todo to. */
    private TaskList taskList;

    private ToDo todo;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the todo.
     * @throws DuplicateTaskException If the todo specified already exists.
     */
    public ToDoCommand(TaskList taskList, String description) throws DuplicateTaskException {
        this.taskList = taskList;
        assert description.length() > 0;
        this.todo = new ToDo(description);
        if (this.taskList.contains(this.todo)) {
            throw new DuplicateTaskException("duplicate todo already exists");
        }
    }

    @Override
    public String get() {
        taskList.add(this.todo);
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}
