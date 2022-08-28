package tuna.command;

import tuna.Storage;
import tuna.TaskList;
import tuna.Ui;

/**
 * Represents a find command. A FindCommand object contains the keyword to search for.
 */
public class FindCommand extends Command {

    /** keyword to search for */
    private String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param inputs String array containing the user inputs.
     */
    public FindCommand(String[] inputs) {
        super(CommandType.FIND);
        this.keyword = inputs[1];
    }

    /**
     * Executes the find command, searching the task list and printing out tasks containing the specified keyword.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.findMessage(tasks.find(keyword));
    }
}
