package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    public UnmarkCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    protected String unmark(ArrayList<String> parsedInput) throws TaskNotFoundException, DukeException {
        try {
            Task task = taskList.unmark(Integer.parseInt(parsedInput.get(1)));
            storage.saveTask(taskList);
            return ui.printUnmarked(task);
        } catch  (NumberFormatException e) {
            throw new DukeException("Your index is invalid!");
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }
}
