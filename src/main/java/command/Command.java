package command;
import wagwan.Storage;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command is an abstract class that serves as a blueprint for all user commands parsed by Parser Class.
*
* @author Linus Chui
*/
public abstract class Command {

    /**
     * The exit boolean decides if the command exits the program.
     */
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the given command and prints out Duke's response.
     *
     * @param tasks the TaskList that encapsulates an ArrayList of Task objects.
     * @param ui the user interface that prints out Duke's response.
     * @param storage the storage that saves changes made to the TaskList.
     * @throws WagwanException if user input is invalid or insufficient.
     */
    public abstract String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException;

    /**
     * Checks if the command exits the program
     *
     * @return true if the command exits the program, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }
}
