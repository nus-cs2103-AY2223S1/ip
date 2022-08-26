package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TasksList;

/**
 * Represents the command to exit and close Duke.
 */
public class ExitCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private Ui ui;

    /**
     * Creates ExitCommand with the given TasksList, Storage, and Ui.
     *
     * @param ui The Ui to end the session with.
     * @param storage The Storage associated with the TasksList.
     * @param tasksList The TasksList to save to Storage.
     */
    public ExitCommand(Ui ui, Storage storage, TasksList tasksList) {
        this.ui = ui;
        this.storage = storage;
        this.tasksList = tasksList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() throws DukeException {
        this.ui.endSession(this.storage, this.tasksList);
    }

    /**
     * Checks if the command given refers to exiting and closing Duke.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to exit is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("bye");
    }
}
