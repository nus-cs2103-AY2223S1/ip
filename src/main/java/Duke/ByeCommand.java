package duke;

/**
 * ByeCommand is a Command that says bye to the user.
 */
public class ByeCommand extends Command {

    /**
     * Executes Bye command and returns closing output to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return closing output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.sayBye();
    }
}
