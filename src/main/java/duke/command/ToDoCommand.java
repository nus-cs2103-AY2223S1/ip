package duke.command;

import java.io.IOException;

import duke.Date;
import duke.DukeException;
import duke.TaskList;
import duke.task.Todo;
import duke.utils.Parser;
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
     * @return String message of running the "todo" command.
     * @throws DukeException To do task has no description.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        int minimumTodoCommandLength = 2;
        if (commandDetails.length >= minimumTodoCommandLength) {
            //Arbitrary date for sorting to do tasks.
            Date date = Parser.parseDate("9999-12-31");
            Todo todo = new Todo(commandDetails[1], date);
            return taskList.addTask(todo, storage);
        } else {
            throw new DukeException("\uD83D\uDE14 OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
