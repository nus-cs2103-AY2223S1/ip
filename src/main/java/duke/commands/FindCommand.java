package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;


/**
 * Represents a class that finds a task containing the keywords and display them to the user
 */
public class FindCommand extends Command {

    public FindCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Finds the task and display them
     *
     * @param parsedInput
     * @return List of tasks containing the keyword to be displayed
     */
    public String findTask(ArrayList<String> parsedInput) {
        ArrayList<Task> searchResult = taskList.find(parsedInput.get(1));
        return ui.printFoundTask(searchResult);
    }
}
