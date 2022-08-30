package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 *
 */
public class FindCommand extends Command {
    private String wordToFind;

    public FindCommand(String word) {
        this.wordToFind = word;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.find(this.wordToFind);
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
