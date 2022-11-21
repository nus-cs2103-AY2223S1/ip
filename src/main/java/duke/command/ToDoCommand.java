package duke.command;

import duke.main.TaskList;
import duke.task.ToDo;

/**
 * A class to handle the adding of todo.
 */
public class ToDoCommand extends AddCommand {
    private String description;

    /**
     * Constructs the todo command.
     *
     * @param description the description of the task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds new tasks.
     *
     * @param taskList
     */
    @Override
    public void add(TaskList taskList) {
        taskList.add(new ToDo(description));
    }
}
