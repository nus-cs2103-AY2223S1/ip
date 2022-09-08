package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a command to add a new Todo task.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private ToDo toDo;

    /**
     * Constructs a Todo Command with the Todo task to be added.
     *
     * @param toDo The Todo task to be added.
     */
    public TodoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    /**
     * {@inheritDoc}
     * This command adds the Todo to the task list.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.add(toDo);
    }

}
