package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String findTask(ArrayList<String> parsedInput) {
        ArrayList<Task> searchResult = taskList.find(parsedInput.get(1));
        return ui.printFoundTask(searchResult);
    }
}
