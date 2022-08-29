package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command to exit and close Duke.
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
    public String execute() throws DukeException {
        this.ui.endSession(this.storage, this.taskList);
        return "Duke: Bye! Hope to see you again soon!";
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
