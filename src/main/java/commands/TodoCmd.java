package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.TodoException;
import exceptions.TumuException;
import tasks.Todo;

/**
 * Class to be executed when a todo command is issued
 * by the user.
 */
public class TodoCmd extends Command {
    private final String body;

    /**
     * Constructor for the TodoCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public TodoCmd(String body) {
        this.body = body;
    }

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException {
        if (body.isBlank()) {
            throw new TodoException();
        } else {
            return addTaskType(new Todo(body), storage, tasks, ui);
        }
    }
}
