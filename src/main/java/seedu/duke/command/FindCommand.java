package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command handles the "find" user input.
 */
public class FindCommand extends Command {

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Prints all the Task within TaskList that matches keyWord.
     * If there are none, print the message indicating so.
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     * @throws DukeException    throw when keyWord is blank
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (keyWord.isBlank()) {
            throw new DukeException(ui.getEmptyKeywordErrorMessage());
        }
        boolean noTaskPrinted = true;
        int searchIndex = 1;
        for (int i = 1; i <= tasks.numOfTasks(); i++) {
            if (tasks.fetchTask(i).toString().contains(keyWord)) {
                if (noTaskPrinted) {
                    ui.showSearchResult();
                }
                noTaskPrinted = false;
                System.out.println(String.format("%d. %s", searchIndex, tasks.fetchTask(i).toString()));
                searchIndex++;
            }
        }
        if (noTaskPrinted) {
            ui.showNoSearchResult();
        }
    }
}
