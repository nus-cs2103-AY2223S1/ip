package duke.command;

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

    /** Description of the todo. */
    private String description;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the todo.
     */
    public ToDoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        assert description.length() > 0;
        this.description = description;
    }

    @Override
    public String get() {
        taskList.add(new ToDo(description));
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}
