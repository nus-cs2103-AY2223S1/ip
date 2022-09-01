package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for the FindCommand to search for tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyWord;

    /**
     * Constructor to create an instance of FindCommand.
     *
     * @param keyWord String to search for in the list of tasks
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the FindCommand to filter the tasks according to the keyword
     * specified by the user and print out all the matching tasks.
     *
     * @param tasks List of tasks to keep track of in the to-do list
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles reading/writing of data to txt file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
        for (int i = 0; i < tasks.size(); i++) {
            String taskInfo = tasks.getTask(i).toString();
            if (taskInfo.contains(keyWord)) {
                System.out.printf("%d. " + taskInfo + "\n", i + 1);
            }
        }

    }

    /**
     * Checks if the command is the ExitCommand to end the ChatBot.
     *
     * @return false since the FindCommand does not terminate the ChatBot
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * String representation to indicate that FindCommand has been successfully executed.
     *
     * @return String to notify user of the following tasks
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Here are the matching tasks in your to-do list:";
    }
}
