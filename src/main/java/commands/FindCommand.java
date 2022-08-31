package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import dukeegg.Ui;
import exceptions.DukeException;
import exceptions.EmptyDescriptionException;

/**
 * Finds all tasks that have a matching description field.
 */
public class FindCommand extends Command {
    private final String[] inputStrings;

    /**
     * Constructs find command, which filters the current tasks with some query and prints them to the console.
     *
     * @param inputStrings The specified input strings.
     */
    public FindCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Filters tasks and prints the tasks that match the query string.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1 || this.inputStrings[1].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        TaskList filteredTasks = tasks.filter(this.inputStrings[1]);
        return ui.showMatchingTasks(filteredTasks);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
