package cinnamon.command.add;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;
import cinnamon.Tasks.Todo;
import cinnamon.Tasks.Task;
import cinnamon.command.Command;

public class AddTodo extends Command {

    String input;

    /**
     * Constructor of an add todo command
     *
     * @param input A string containing task information
     */
    public AddTodo(String input){
        this.input = input;
    };

    /**
     * Adds a todo task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(input.substring(5));
        if (this.input.length() <= 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.add(todo);
        assert (ui != null);
        return ui.addTask() + ui.printTodo(todo);
    }
}
