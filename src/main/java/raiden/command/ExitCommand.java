package raiden.command;

import raiden.Main;
import raiden.RaidenException;
import raiden.Storage;
import raiden.Ui;
import raiden.task.TaskList;

/**
 * Represents the command to exit and close Raiden.
 */
public class ExitCommand extends Command {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Creates ExitCommand with the given TaskList, Storage, and Ui.
     *
     * @param ui The Ui to end the session with.
     * @param storage The Storage associated with the TaskList.
     * @param taskList The TaskList to save to Storage.
     */
    public ExitCommand(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws RaidenException {
        this.ui.endSession(this.storage, this.taskList);
        Main.closeStage();
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Checks if the command given refers to exiting and closing Raiden.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to exit is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("bye") || s.equals("exit");
    }
}
