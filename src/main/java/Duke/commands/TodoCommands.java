package Duke.commands;

import java.util.ArrayList;
import java.util.List;
import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Duke.tasks.Task;
import Duke.tasks.Todo;
import java.io.IOException;

public class TodoCommands extends Executor {


    /**
     * Class constructor.
     *
     * @param input The input provided by the user.
     */


    public TodoCommands(String input) {
        super(input);
        assert input.startsWith("todo");
    }


    /**
     * Executes the command to create a new Todo task, saves the new task in the task list and
     * prints the size of the list after the command execution.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String line1 = "I've added this task:";
        Task addedTask = tasks.addTask(new Todo(description));
        String line2 = addedTask.toString();
        storage.addTask(addedTask);
        String line3 = super.execute(tasks, ui, storage);
        String reply = line1 + "\n" + line2 + "\n" + line3;
        return reply;
    }
}
