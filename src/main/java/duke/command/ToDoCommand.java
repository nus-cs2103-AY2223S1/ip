package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a command to add a new ToDo task.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private ToDo toDo;

    /**
     * Constructs a ToDo Command with the ToDo task to be added.
     *
     * @param toDo The Todo task to be added.
     */
    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    /**
     * {@inheritDoc}
     * This command adds the ToDo to the task list.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.add(toDo);
    }

}
