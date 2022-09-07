package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to find tasks with the keyword.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructs a command to find tasks with the given keyword input.
     *
     * @param input The specified keyword.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Wait a minute :/ "
                    + "what are you finding??");
        }
        TaskList tasksWithWord = taskList.getTasksWithWord(input);
        ui.showFindings(tasksWithWord, input);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
