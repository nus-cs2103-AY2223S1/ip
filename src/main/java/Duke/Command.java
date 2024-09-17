package duke;

/**
 * Command class represents the various processes in the Duke app.
 */
public abstract class Command {

    /**
     * Executes command and returns output to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return string output to user.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
