package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;

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
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        if (input.isBlank()) {
            throw new GinaException("Wait a minute :/ "
                    + "what are you finding??");
        }
        TaskAndContactList tasksWithWord = taskAndContactList.getTasksWithWord(input);
        return ui.showFindings(tasksWithWord, input);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
