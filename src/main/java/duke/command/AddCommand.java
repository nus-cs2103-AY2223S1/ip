package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

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
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.saveFile(tasks);

        ArrayList<String> responseLines = new ArrayList<>();
        responseLines.add("Got it. I've added this task:");
        responseLines.add(" " + task);
        responseLines.add(ui.getTaskCount(tasks.taskCount()));
        return String.join("\n", responseLines);
    }
}
