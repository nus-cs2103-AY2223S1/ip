package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * FindCommands searches for all tasks that contain the word to find.
 */
public class FindCommand extends Command {
    private String wordToFind;

    /**
     * @param word Word to find in all the tasks' description.
     */
    public FindCommand(String word) {
        this.wordToFind = word;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.find(this.wordToFind);
    }

    /**
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @param o Other object we are comparing with
     * @return whether each objects are of the same type
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof FindCommand;
    }
}
