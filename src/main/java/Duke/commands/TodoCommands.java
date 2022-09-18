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
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<String> text = new ArrayList<>();
        text.add("I've added this task:");
        Task addedTask = tasks.addTask(new Todo(description));
        text.add(addedTask.toString());
        storage.addTask(addedTask);
        text.addAll(super.execute(tasks, ui, storage));
        return text;
    }
}
