package tuna.command;

import java.util.Arrays;

import tuna.TunaException;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents an add Event Task command. An AddEventCommand object contains the index of the /at command,
 * task description and when the event is occurring.
 */
public class AddEventCommand extends Command {
    /** Index of the /at command */
    private int limit;
    /** Description of the task */
    private String taskDescription;
    /** When the event is occurring */
    private String at;

    /**
     * Creates a AddEventCommand object.
     *
     * @param inputs String array containing the user inputs.
     * @throws TunaException exception thrown when /at is missing.
     */
    public AddEventCommand(String[] inputs) throws TunaException {
        super(CommandType.EVENT);
        limit = findElem(inputs, "/at");
        if (limit == -1) {
            throw new TunaException("Oops! Remember to include /at and the event time after your task description");
        }
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        at = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
    }

    /**
     * Executes the add event command, adding the Event Task to the tasks list.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        tasks.addEvent(taskDescription, at);
        return ui.taskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}

