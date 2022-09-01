package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs find all task with keyword in TaskList command.
 */
public class FindCommand implements Command {
    /** Keyword to search for in tasks */
    private String keyword;

    /**
     * Constructor of class FindCommand
     *
     * @param keyword String of keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return Ui.formatFindTaskString(taskList, keyword);
    }
}
