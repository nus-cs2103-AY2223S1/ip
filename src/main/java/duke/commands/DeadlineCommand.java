package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class DeadlineCommand extends Command {

    public DeadlineCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String addDeadline(ArrayList<String> parsedInput) throws DukeException {
        Deadline deadlineTask = new Deadline(parsedInput.get(1), parsedInput.get(2), false);
        assert deadlineTask.isDeadline() : "Task should be a Deadline!";
        taskList.addTask(deadlineTask);
        storage.saveTask(taskList);
        return ui.printAddedTask(deadlineTask, taskList);
    }
}
