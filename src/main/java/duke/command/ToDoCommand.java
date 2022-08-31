package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.task.Todo;
import duke.utils.Storage;

/**
 * Handles a "todo" command.
 * @author Jason
 */
public class ToDoCommand extends Command {
    private String[] commandDetails;

    public ToDoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Handles a to do task.
     * @param taskList TaskList to add to do task to.
     * @param storage Storage to save new to do task.
     * @throws DukeException To do task has no description.
     */
    @Override
    public void run(TaskList taskList, Storage storage) throws DukeException, IOException {
        if (commandDetails.length >= 2) {
            Todo todo = new Todo(commandDetails[1]);
            taskList.addTask(todo, storage);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
