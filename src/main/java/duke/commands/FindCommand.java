package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;

/**
 * This class performs find all task with keyword in TaskList command.
 */
public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
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
        return Ui.formatFindTaskMessage(taskList, keyword);
    }
}
