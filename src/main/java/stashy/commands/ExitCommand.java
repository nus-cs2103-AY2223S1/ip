package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to exit the application.
 */
public class ExitCommand extends Command {
    public static final String KEYWORD = "bye";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nExits the chatbot."
        + "\n\nExample: bye";
    private boolean showHelp;

    /**
     * Constructor method.
     *
     * @param showHelp Whether to show help or not
     */
    public ExitCommand(boolean showHelp) {
        this.showHelp = showHelp;
    }

    /**
     * Overloaded constructor method to show help.
     */
    public ExitCommand() {
        this(true);
    }

    @Override
    public boolean isExit() {
        return !this.showHelp;
    }

    /**
     * Exits the conversation before saving the updated task list
     * into the storage and quitting the application.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.showHelp) {
            ui.showIndented(HELP_MESSAGE);
        } else {
            storage.writeTaskListToFile(tasks);
            ui.showGoodbye();
        }
    }

    /**
     * String version of the execute method.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.showHelp) {
            return HELP_MESSAGE;
        } else {
            storage.writeTaskListToFile(tasks);
            return ui.showGoodbyeString();
        }
    }
}
