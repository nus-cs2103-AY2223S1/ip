package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents the class that deletes a task from the list
 */
public class DeleteCommand extends Command {

    public DeleteCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Deletes the task specified and informs the user
     *
     * @param parsedInput
     * @return Response to be displayed and how many tasks are left
     * @throws TaskNotFoundException
     */
    public String deleteTask(ArrayList<String> parsedInput) throws TaskNotFoundException {
        try {
            Task task = taskList.delete(Integer.parseInt(parsedInput.get(1)));
            storage.saveTask(taskList);
            return ui.printDeletedTask(task, taskList);
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }
}
