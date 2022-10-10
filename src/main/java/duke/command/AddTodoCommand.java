package duke.command;

import duke.task.ToDo;

/**
 * Represents a AddTodoCommand to add ToDo task. This class extends AddCommand class.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * This method is a AddTodoCommand constructor.
     * A AddTodoCommand consists of a TaskList, a task description string.
     * @param taskList a list of tasks.
     * @param description task description string.
     */
    public AddTodoCommand(duke.TaskList taskList, String description) {
        super(taskList, description);
    }

    /**
     * Executes add todo command to adda ToDo task to the TaskList.
     * Returns a string containing commandType "todo" and task index (1-indexed).
     * Returned value will be used to call chatbot response message.
     *
     * @return a string containing commandType "todo" and task index.
     */
    public String execute() {
        super.execute(new ToDo(super.description));
        return String.format("todo %d", super.tasks.getSize());
    }
}
