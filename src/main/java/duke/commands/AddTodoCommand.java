package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.UI;

/**
 * AddTodoCommand implements method for adding Todo to task list.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class AddTodoCommand extends Command {

    private final String input;

    /**
     * Creates new AddTodoCommand object.
     *
     * @param input the input string from the user
     * @throws DukeException to handle if the input string is invalid.
     */
    public AddTodoCommand(String input) throws DukeException {
        if (!checkValid(input)) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.input = input;
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param taskList the destination task list for the todo to be added
     * @param ui the ui to display message after the task is added
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        // Index of beginning of Todo description after the "todo" keyword in command.
        String taskDesc = input.substring(5);
        Todo todo = new Todo(taskDesc);
        taskList.addTask(todo);
        ui.addTaskMessage(todo, taskList.getSize());
        storage.store(taskList);
    }

    /**
     * Prevents the program from terminating in Duke.run().
     *
     * @return False as this is not the 'exit' command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
