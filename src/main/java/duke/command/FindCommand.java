package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command for finding tasks in Duke.
 */
public class FindCommand implements Command {
    private String toFind;

    /**
     * Constructs a FindCommand.
     *
     * @param toFind String to match with.
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * Executes the find command and print the tasks which matches toFind.
     *
     * @param tasks TaskList containing list of task.
     * @param ui Ui created when starting Duke.
     * @param storage Storage created when starting Duke.
     * @throws DukeException Exception from executing command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.dukePrint(tasks.find(toFind));
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return False since this is not an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
