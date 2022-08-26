package duke.command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for an ExitCommand to terminate the ChatBot.
 */
public class ExitCommand extends Command {

    /**
     * Default constructor method to create an instance of ExitCommand.
     *
     * @return an instance of ExitCommand
     */
    public ExitCommand() {
    }

    /**
     * Executes the ExitCommand which prints the goodbye message.
     *
     * @param tasks a list that keeps track of the tasks added/removed
     * @param ui ui that handles the interaction with user inputs
     * @param storage storage that handles the writing/reading of data from a txt file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
    }

    /**
     * Check if it is the ExitCommand in order to exit loop
     *
     * @return true since it is the ExitCommand
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Farewell message to the user when the ExitCommand is executed.
     *
     * @return a String representation of the farewell message
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Adios Amigo! See you soon!";
    }
}
