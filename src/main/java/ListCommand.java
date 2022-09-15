package anya;

import java.util.List;

/**
 * Represents a command to list all the task in the current tasklist.
 */
public class ListCommand extends Command {

    ListCommand() {

    }

    /**
     * Execute the list command.
     * @param tasks current tasklist.
     * @param ui interaction class.
     * @return the response of the anya.
     */
    String execute(TaskList tasks, Ui ui) {
        List<Task> fullTasks = tasks.getList();
        String response = ui.printList(fullTasks);
        return response;
    }

}
