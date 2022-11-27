package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.DukeMissingParameterException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command for finding tasks in Duke.
 */
public class FindCommand implements Command {
    private String toFind;

    /**
     * Constructs a FindCommand.
     *
     * @param inputs Array of command parsed by parser.
     */
    public FindCommand(String[] inputs) {
        try {
            this.toFind = inputs[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingParameterException("find <word>", "word to find");
        }
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
