package duke;

/**
 * HelpCommand is a Command that displays a URL to the Duke help page.
 */
public class HelpCommand extends Command {

    /**
     * Executes HelpCommand and returns the URL to the help page.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.helpResponse();
    }
}
