package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * FindCommand looks for tasks in the task list based on user input.
 */
public class FindCommand extends Command{

    /**
     * Command line input for find command.
     */
    private String input;

    /**
     * Creates a new FindCommand.
     *
     * @param input String used for find command.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks in the list that contain the matching command.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> found = tasks.find(input);

        if (found.isEmpty()) {
            return "There are no matching tasks in your list!\n";
        } else {
            StringBuilder toReturn = new StringBuilder();
            toReturn.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < found.size(); i++) {
                toReturn.append((i+1) + ". " + found.get(i).toString() + "\n");
            }
            return toReturn.toString();
        }
    }
}
