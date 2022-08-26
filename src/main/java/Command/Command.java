package Command;
import Duke.DukeUi;
import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;

/**
* Command is an abstract class that serves as a blueprint for all user commands parsed by Parser Class.
*
* @author Linus Chui
*/
public abstract class Command {

    /**
     * The exit boolean decides if the command exits the program.
     */
    private boolean exit;

    public Command() {
        this.exit = false;
    }

    /**
     * Executes the given command and prints out Duke's response.
     *
     * @param tasks the TaskList that encapsulates an ArrayList of Task objects.
     * @param ui the user interface that prints out Duke's response.
     * @param storage the storage that saves changes made to the TaskList.
     * @throws DukeException if user input is invalid or insufficient.
     */
    public abstract void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException;

    /**
     * Checks if the command exits the program
     *
     * @return true if the command exits the program, false otherwise
     */
    public boolean isExit() {
        return this.exit;
    }
}