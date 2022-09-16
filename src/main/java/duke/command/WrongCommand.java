package duke.command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for a WrongCommand when the user key in keywords that are
 * not recognised by the ChatBot.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class WrongCommand extends Command {

    /**
     * Executes the WrongCommand by informing the user that the ChatBot does not recognise
     * the command and the ChatBot will request for another input.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String that represents the response of ChatBot after executing the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.toString();
    }

    /**
     * Gives a String representation of successfully executing the WrongCommand.
     *
     * @return String to notify the user that the input command is not recognised
     */
    @Override
    public String toString() {
        return "________________________________________\n"
                + "Sorry! I don't know what that means :(\n"
                + "________________________________________";
    }
}
