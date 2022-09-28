package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents all commands related to adding all kinds of tasks (Todo, Event, Deadline).
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to list and prints the task and task count.
     * Also saves the updated tasks to storage.
     *
     * @param tasks List of tasks.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.saveFile(tasks);

        assert tasks.taskCount() > 0 : "Correctly adding tasks should guarantee a non-zero task count.";

        ArrayList<String> responseLines = new ArrayList<>();
        responseLines.add("Got it. I've added this task:");
        responseLines.add(" " + task);
        responseLines.add(Ui.getTaskCount(tasks.taskCount()));
        return String.join("\n", responseLines);
    }
}
