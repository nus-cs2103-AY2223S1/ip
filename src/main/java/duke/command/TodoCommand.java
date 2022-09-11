package duke.command;

import duke.Constants;
import duke.DukeException;
import duke.Response;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Adds Todo task in duke's tasklist.
 */
public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(this.description);
        taskList.addTask(todo);
        String message = "+ Added this todo:\n" + todo + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n";
        Response response = new Response(message, false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
