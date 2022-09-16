package duke.commands;

import java.io.IOException;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Todo;
import duke.exception.DukeException;
import duke.exception.DuplicateException;

/**
 * The TodoCommand class encapsulates the execution of a todo command.
 */
public class TodoCommand extends Command{
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the todo command.
     *
     * @param taskList List where a specified todo is to be added to it.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Todo todo = new Todo(this.input);
        if (taskList.detectDuplicate(todo)) {
            throw new DuplicateException();
        } else {
            taskList.append(todo);
            String todoMessage = "added: " + todo.toString() + "\n";
            storage.saveTasks(taskList);
            return ui.print(todoMessage, taskList);
        }
    }
}
