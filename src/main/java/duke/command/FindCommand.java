package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    /**
     * Command line input for find command.
     */
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks in the list that contain the matching command.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> found = tasks.find(input);

        if (found.isEmpty()) {
            System.out.println("There are no matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < found.size(); i++) {
                System.out.println((i+1) + ". " + found.get(i).toString());
            }
        }
    }
}
