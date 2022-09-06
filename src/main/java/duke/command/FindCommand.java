package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.ui.Ui;
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
     * @param storage Storage created when starting Duke.
     * @throws DukeException Exception from executing command.
     */
    public String execute(TaskList tasks, Storage storage) {
        return tasks.find(toFind);
    }
}
