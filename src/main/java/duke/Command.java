/**
 * This class handles all user commands.
 */

package duke;

public abstract class Command {

    /**
     * Constructor for the command class.
     */
    public Command() {

    }

    /**
     * Returns an updated tasklist after executing user command.
     *
     * @param taskList Tasklist to be edited.
     * @param ui Ui for display.
     * @param storage Storage to write/read from file.
     * @return Updated TaskList.
     */
    public abstract TaskList execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns a boolean to check if user has exited the program.
     *
     * @return true if exited and false otherwise.
     */
    public abstract boolean isExit();
}

