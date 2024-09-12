package duke;

import java.util.ArrayList;

/**
 * Represents a command which shows the list of tasks stored.
 */
public class ShowListCommand extends Command {
    /**
     * Excecutes the command to show the list of tasks stored.
     * @param taskName Name of task.
     * @param listOfTasks List where tasks are stored.
     * @param ui Ui object that is used to interact with users.
     * @param storage Storage to store the file of the list of tasks.
     * @return A string that would be outputted to the screen when user wants to display the list.
     */
    @Override
    String execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        TaskList taskList = new TaskList(listOfTasks);
        return taskList.showList();
    }
}
