package duke.command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic of a ListCommand when the user inputs the keyword list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by iterating through the TaskList and printing out each task in a single line,
     * consisting of the task type (T, D, E), task status (completed tasks are marked with X) and
     * task description.
     *
     * @param tasks a list that keeps track of the tasks added/removed
     * @param ui ui that handles the interaction with user inputs
     * @param storage storage that handles the writing/reading of data from a txt file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. " + tasks.getTask(i).toString() + "\n", i + 1);
        }
    }

    /**
     * Check if it is the exit command in order to exit loop
     *
     * @return false since a ListCommand does not end the ChatBot
     */
    public boolean isExit() {
        return false;
    }

    /**
     * A String representation of successfully executing the ListCommand.
     *
     * @return a String to notify the user of the tasks he has in his to-do list
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Here are the tasks in your to-do list:";
    }
}
