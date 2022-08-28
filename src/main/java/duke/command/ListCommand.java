package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the list command to list all the user's tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out list of tasks individually with index.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> responseLines = new ArrayList<>();
        responseLines.add("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.taskCount(); i++) {
            Task task = tasks.getTask(i);
            responseLines.add(i + ". " + task);
        }
        return String.join("\n", responseLines);
    }
}
