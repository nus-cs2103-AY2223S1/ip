package duke.command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic of a ListCommand when the user inputs the keyword list.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand and printing out each task in a single line,
     * consisting of the task type (T, D, E), task status (completed tasks are marked with X) and
     * task description.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String that represents the response of ChatBot after executing the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this + tasks.compileAllTasks()
                + "\n________________________________________";
    }

    /**
     * Gives a String representation of successfully executing the ListCommand.
     *
     * @return String to notify the user of the tasks he has in his to-do list
     */
    @Override
    public String toString() {
        return "________________________________________\n"
                + "Here are the tasks in your to-do list:";
    }
}
