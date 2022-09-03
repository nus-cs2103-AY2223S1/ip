package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

public class FindCommand extends Command {
    private static final String EMPTY_KEYWORD_ERROR_MESSAGE = "Eh your keyword cannot be empty lah!";
    private static final String FIND_TASKS_PREFIX_MESSAGE = "Boss ah, this one all your tasks matching '";
    private static final String FIND_TASKS_SUFFIX_MESSAGE = "' : ";
    private static final String NO_MATCHING_TASKS_MESSAGE = "Boss, no matching tasks ah";

    public FindCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

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
