package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for the FindCommand to search for tasks by keyword.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class FindCommand extends Command {
    private String[] keyWord;

    /**
     * Constructor to create an instance of FindCommand.
     *
     * @param keyWord Array of String keywords to search for in the list of tasks
     */
    public FindCommand(String... keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the FindCommand to filter the tasks according to the keyword
     * specified by the user and print out all the matching tasks.
     *
     * @param tasks List of tasks to keep track of in the to-do list
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles reading/writing of data to txt file
     * @return String that represents the response of ChatBot after executing the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder respondMessage = new StringBuilder(this.toString());
        for (int i = 0; i < this.keyWord.length; i++) {
            respondMessage.append(tasks.findTasks(this.keyWord[i]));
        }
        return respondMessage.append("\n________________________________________").toString();
    }

    /**
     * String representation to indicate that FindCommand has been successfully executed.
     *
     * @return String to notify user of the following tasks
     */
    @Override
    public String toString() {
        return "________________________________________\n"
                + "Here are the matching tasks in your to-do list:";
    }
}
