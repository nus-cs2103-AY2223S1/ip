package duke.commands;

import duke.exceptions.DukeDateException;
import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents a class that creates Deadline Task and adds it to the list
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Creates a new Deadline Task and adds it to the list
     *
     * @param parsedInput
     * @return Response to be displayed
     * @throws DukeException
     */
    public String addDeadline(ArrayList<String> parsedInput) throws DukeException {
        try {
            Deadline deadlineTask = new Deadline(parsedInput.get(1), parsedInput.get(2), false);
            assert deadlineTask.isDeadline() : "Task should be a Deadline!";
            taskList.addTask(deadlineTask);
            storage.saveTask(taskList);
            return ui.printAddedTask(deadlineTask, taskList);
        } catch (DukeDateException e) {
            return e.getMessage();
        }
    }
}
