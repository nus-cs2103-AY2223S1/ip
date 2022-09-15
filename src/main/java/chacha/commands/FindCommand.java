package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

/**
 * Represents Find Command.
 */
public class FindCommand extends Command {
    private String[] keywords;

    /**
     * Constructor for FindCommand with keyword to find.
     * 
     * @param keywords Keyword to use in find.
     */
    public FindCommand(String[] keywords) {
        this.keywords = keywords;
    }   

    /**
     * Executes command to find keyword in task list.
     *
     * @param ui Ui to print output message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList newTasks = taskList.find(keywords);
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