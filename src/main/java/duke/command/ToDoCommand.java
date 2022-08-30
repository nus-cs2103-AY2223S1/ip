package duke.command;

import duke.component.TaskList;
import duke.task.ToDo;

/**
 * Represents a command to add a ToDo to the TaskList.
 */
public class ToDoCommand extends Command {

    /**
     * Constructs a new ToDoCommand.
     *
     * @param content Content of the user command.
     * @param tasks List of all tasks.
     */
    public ToDoCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    /**
     * Adds the ToDo to the TaskList.
     *
     * @return String representation of the message in response to the command.
     */
    @Override
    public String run() {
        return this.tasks.addTask(new ToDo(this.content));
    }
}
