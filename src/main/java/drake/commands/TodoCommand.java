package drake.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;
import drake.tasks.Task;
import drake.tasks.Todo;

/**
 * Represents a command given by the user to create a new to-do task.
 */
public class TodoCommand extends CreateTaskCommand {

    /**
     * Constructor.
     *
     * @param fullInput The user input.
     */
    public TodoCommand(String fullInput) {
        super(fullInput);
    }

    /**
     * Executes the command to create a new to-do task, saving the new task and
     * printing the size of the task list after execution.
     *
     * @param tasks   The task list before the command is executed.
     * @param ui      Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @return The list of replies.
     * @throws IOException    when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        ArrayList<String> reply = new ArrayList<>();
        reply.add("I've added this task:");
        Task addedTask = tasks.addTask(new Todo(description));
        reply.add(addedTask.toString());
        storage.addTask(addedTask);
        reply.addAll(super.execute(tasks, ui, storage));
        return reply;
    }
}
