package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * FindCommand class represents the find command given by the user.
 */
public class FindCommand extends Command{
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
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        ArrayList<Task> tempTaskArray = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).isContainsKeyword(keyword)) {
                tempTaskArray.add(taskList.getTask(i));
            }
        }
        if (tempTaskArray.size() == 0) {
            String message = "\t" + "No search results are available for this keyword!";
            ui.displayCommandMessage(message, null, null);
        } else {
            String findMessage = "\t" + "Here are the list of matching tasks!";
            ui.displayCommandMessage(findMessage,null,null);
            for (int j = 0; j < tempTaskArray.size(); j++) {
                String itemDisplayed = String.format("\t\t\t%d. %s", j + 1, tempTaskArray.get(j));
                ui.displayCommandMessage(itemDisplayed, null, null);
            }
        }
        ui.printBorder();
    }
}
