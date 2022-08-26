package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.EmptyDescriptionException;

/**
 * Creates a new event
 */
public class FindCommand extends Command {
    private final String[] inputStrings;

    public FindCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1 || this.inputStrings[1].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        TaskList filteredTasks = tasks.filter(this.inputStrings[1]);
        ui.showMatchingTasks(filteredTasks);
    }

    public boolean isExit() {
        return false;
    }
}
