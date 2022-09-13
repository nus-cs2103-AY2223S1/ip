package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand is the Command when the user wants to exit Duke.
 */
public class ExitCommand extends Command {
    private static final String EMPTY = "";
    /**
     * Initializes an ExitCommand object.
     */
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    /**
     * Stores the user's tasks.
     * Exits Duke.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.store(tasks);
        } catch (Exception e) {
            System.out.print(EMPTY);
        } finally {
            return ui.showBye();
        }
    }
}
