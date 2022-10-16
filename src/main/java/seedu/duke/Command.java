package seedu.duke;

/**
 * An abstract class for Command.
 */
public abstract class Command {

    /**
     * Performs the respective actions with accordance to the command.
     *
     * @param tasklist Tasklist containing an arraylist of tasks.
     * @param ui Ui of the application.
     * @param storage Storage which handles the reading and saving of tasks to hard disk.
     * @param input The user's input in the command line
     * @return a string to be displayed in the GUI when the command is executed.
     * @throws DukeException if command is not found or cannot be executed.
     */
    public abstract String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException;

}
