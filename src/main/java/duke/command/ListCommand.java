package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all the tasks currently stored in a specified task list.
 */
public class ListCommand extends Command {

    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command and list all tasks in the task list.
     *
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Ui ui, Storage storage, TaskList taskList) {
        return taskList.getList();
    }
}
