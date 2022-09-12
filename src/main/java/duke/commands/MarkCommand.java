package duke.commands;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {

    public MarkCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    protected String mark(ArrayList<String> parsedInput) throws  DukeException {
        try {
            Task task = taskList.mark(Integer.parseInt(parsedInput.get(1)));
            storage.saveTask(taskList);
            return ui.printMarked(task);
        } catch  (NumberFormatException e) {
            throw new DukeException("I can't find the task you want to mark!");
        }
    }
}
