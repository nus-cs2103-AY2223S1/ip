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
     */
    public ExitCommand() {
    }

    /**
     * Executes the ExitCommand which prints the goodbye message.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
    }

    /**
     * Checks if it is the ExitCommand in order to exit loop.
     *
     * @return true since it is the ExitCommand
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Farewell message to the user when the ExitCommand is executed.
     *
     * @return String representation of the farewell message
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Adios Amigo! See you soon!";
    }
}
