package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;
import duke.tasks.ToDo;

/**
 * This class performs create a todo task with specified description
 * to add to TaskList command.
 */
public class TodoCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    private ToDo todo;

    /**
     * Constructor for class TodoCommand.
     *
     * @param description Description of task.
     */
    public TodoCommand(String description) {
        this.todo = new ToDo(description, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(todo);
        storage.appendToFile(todo);
        return Ui.formatAddTaskMessage(todo);
    }
}
