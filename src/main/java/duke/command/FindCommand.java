package duke.command;

import java.util.ArrayList;

import duke.CommandHistory;
import duke.MultiLineFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * FindCommand class represents the find command given by the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor of the Find Class.
     * Sets the keyword which filters the task to
     * local variable.
     *
     * @param keyword The keyword used for filtering.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters out all tasks which contains the keyword and
     * returns the list of tasks which contains the keyword.
     *
     * @param ui Ui object which handles the interaction with the user
     * @param storage Storage object which handles interaction with data in file
     * @param taskList List of tasks
     * @param commandHistory History of commands made.
     * @return The string of list of tasks which contains the keyword
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        ArrayList<Task> tempTaskArray = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).hasKeyword(keyword)) {
                tempTaskArray.add(taskList.getTask(i));
            }
        }
        if (tempTaskArray.size() == 0) {
            String message = "No search results are available for this keyword!";
            return ui.displayCommandMessage(message, null, null);
        } else {
            MultiLineFormatter mFormatter = new MultiLineFormatter();
            String findMessage = "Here are the list of matching tasks!";
            mFormatter.add(findMessage);
            mFormatter.add("\n");
            for (int j = 0; j < tempTaskArray.size(); j++) {
                String itemDisplayed = String.format("\t\t%d. %s", j + 1, tempTaskArray.get(j));
                mFormatter.add(itemDisplayed);
                mFormatter.add("\n");
            }
            return mFormatter.getFullMessage();
        }
    }

    /**
     * Returns message that this command cannot be undone.
     *
     * @param ui Ui object which handles the interaction with the user
     * @param storage Storage object which handles interaction with data in file
     * @param taskList List of tasks
     * @param commandHistory History of commands made.
     * @return Message that this command cannot be undone.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        return "There are no more changes to be undone!";
    }
}
