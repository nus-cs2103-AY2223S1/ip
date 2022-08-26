package duke.command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for a WrongCommand when the user key in keywords that are
 * not recognised by the ChatBot.
 */
public class WrongCommand extends Command {

    /**
     * Executes the WrongCommand by informing the user that the ChatBot does not recognise
     * the command and the ChatBot will request for another input.
     *
     * @param tasks a list that keeps track of the tasks added/removed
     * @param ui ui that handles the interaction with user inputs
     * @param storage storage that handles the writing/reading of data from a txt file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
    }

    /**
     * Check if it is the exit command in order to exit loop
     *
     * @return false since a WrongCommand does not end the ChatBot
     */
    public boolean isExit() {
        return false;
    }

    /**
     * A String representation of successfully executing the WrongCommand.
     *
     * @return a String to notify the user that the input command is not recognised
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "OOPS! I'm sorry, but I don't know what that means :(";
    }
}
