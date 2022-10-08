package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>FindCommand class</h1>
 * Class that finds Tasks with the matching keyword
 * and prints them in the Ui
 */
public class FindCommand extends Command {
    private static final String EMPTY_KEYWORD_ERROR_MESSAGE = "Eh your keyword cannot be empty lah!";
    private static final String FIND_TASKS_PREFIX_MESSAGE = "Boss ah, this one all your tasks matching '";
    private static final String FIND_TASKS_SUFFIX_MESSAGE = "' : ";
    private static final String NO_MATCHING_TASKS_MESSAGE = "Boss, no matching tasks ah";

    /**
     * Creates the FindCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public FindCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Finds Tasks with the matching keyword and prints them in the Ui
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException the exception to be thrown when Duke encounters an issue.
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String keyword = input.substring(4).trim();
        if (keyword.equals("")) {
            throw new DukeException(EMPTY_KEYWORD_ERROR_MESSAGE);
        }
        TaskList newTaskList = getMatchingTasks(keyword);
        ui.printTasks(newTaskList, FIND_TASKS_PREFIX_MESSAGE + keyword
                + FIND_TASKS_SUFFIX_MESSAGE, NO_MATCHING_TASKS_MESSAGE, dialogContainer, userDialog);
    }

    private TaskList getMatchingTasks(String keyword) {
        TaskList newTaskList = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.containsKeyword(i, keyword)) {
                newTaskList.add(tasks.get(i));
            }
        }
        return newTaskList;
    }
}
