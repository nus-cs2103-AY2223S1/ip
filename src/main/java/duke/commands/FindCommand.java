package duke.commands;

import java.io.IOException;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;
import java.util.ArrayList;

/**
 * The FindCommand class encapsulates the execution of a delete command.
 */
public class FindCommand extends Command{
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes a delete command.
     * @param taskList Tasklist from which tasks that match search criteria is listed.
     * @param ui Ui sends a message to user after successful execution of command or when an error is thrown.
     * @param storage Storage
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException{
        ArrayList<Task> hit = new ArrayList<>();
        for (int i = 0; i < taskList.length(); i++) {
            Task task = taskList.index(i);
            String description = task.getDescription();
            if (description.toUpperCase().contains(this.input.toUpperCase())) {
                hit.add(task);
            }
        }
        if (hit.size() == 0) {
            throw new DukeException("There are no matching results.");
        } else {
            String message = "Here are the matching tasks in your list:\n";
            for (int j = 0; j < hit.size(); j++) {
                if (j == hit.size() - 1) {
                    message += String.format("%d. " + hit.get(j), j + 1);
                } else {
                    message += String.format("%d. " + hit.get(j) + "\n", j + 1);
                }
            }
            ui.print(message);
        }
    }
}
