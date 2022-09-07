package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * Represents the command to create a to-do.
 */
public class ToDoCommand extends Command {
    private String input;

    /**
     * Constructs command to create a new To-Do task with the specified description input.
     *
     * @param input The user input for task description.
     */
    public ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Hold up! Description cannot be empty!");
        }
        ToDo newTodo = new ToDo(input);
        taskList.addTask(newTodo);
        ui.showAddTask(newTodo, taskList);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
