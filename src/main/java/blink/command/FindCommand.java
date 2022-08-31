package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Task;

import java.util.ArrayList;

/**
 * Finds all the Tasks inside TaskList with the same keyword.
 */
public class FindCommand extends Command{

    private String keyWord;

    /**
     * Constructor for FindCommand.
     *
     * @param input Keyword to search for Tasks
     */
    public FindCommand(String input) {
        this.keyWord = input;
    }

    /**
     * Runs by searching TaskList for all the Tasks with the keyword specified
     * and displays those Tasks.
     *
     * @param tasks TaskList object of the current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> findList = tasks.find(this.keyWord);
        return ui.showFind(findList, this.keyWord);
    }

    /**
     * Find command will not end the program.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
