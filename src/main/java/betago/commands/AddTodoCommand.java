package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

/**
 * AddEventCommand class that adds a todo task into the tasklist upon execution.
 */
public class AddTodoCommand implements Command {

    /**
     * Adds a todo task to the tasklist.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Output to the user after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.addTodo(str);
            storage.saveItems();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
