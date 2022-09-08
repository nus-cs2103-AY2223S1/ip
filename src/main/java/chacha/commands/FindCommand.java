package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

/**
 * Represents Find Command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand with keyword to find.
     * 
     * @param keyword Keyword to use in find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }   

    /**
     * Executes command to find keyword in task list.
     *
     * @param tasks Task list to find keyword in.
     * @param ui Ui to print output message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList newTasks = taskList.find(keyword);
        ui.printFind(newTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}