package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * A class representing a find command.
 */
public class FindCommand implements Command {
    private String toFind;

    /**
     * Constructs a FindCommand
     *
     * @param toFind String to match with
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * Executes the find command and print the tasks which matches toFind.
     *
     * @param tasks TaskList containing list of task
     * @param ui Duke Ui
     * @param storage Duke Storage
     * @throws DukeException exception from executing command
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.dukePrint(tasks.find(toFind));
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
