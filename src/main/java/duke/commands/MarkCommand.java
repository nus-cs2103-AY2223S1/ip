package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents a class that marks a task as not done
 */

public class MarkCommand extends Command {

    public MarkCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Marks the function as done and returns the response to be displayed by Duke
     *
     * @param parsedInput input after being parsed by Parser
     * @return Response String to be displayed
     * @throws TaskNotFoundException
     * @throws DukeException
     */
    protected String mark(ArrayList<String> parsedInput) throws  DukeException, TaskNotFoundException {
        try {
            Task task = taskList.mark(Integer.parseInt(parsedInput.get(1)));
            storage.saveTask(taskList);
            return ui.printMarked(task);
        } catch  (NumberFormatException e) {
            throw new DukeException("I can't find the task you want to mark!");
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }
}
