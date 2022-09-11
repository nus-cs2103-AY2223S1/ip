package ado.command;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;
import ado.task.Todo;

/**
 * Adds Todo task in Ado's tasklist.
 */
public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
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
