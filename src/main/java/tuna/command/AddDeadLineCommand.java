package tuna.command;

import java.util.Arrays;

import tuna.TunaException;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents an add Deadline Task command. An AddDeadLineCommand object contains the index of the /by command,
 * task description and the deadline.
 */
public class AddDeadLineCommand extends Command {

    /** Index of the /by command */
    private int limit;
    /** Description of the task */
    private String taskDescription;
    /** Deadline of the task */
    private String by;

    /**
     * Creates a AddDeadLineCommand object.
     *
     * @param inputs String array containing the user inputs.
     * @throws TunaException exception thrown when /by is missing.
     */
    public AddDeadLineCommand(String[] inputs) throws TunaException {
        super(CommandType.DEADLINE);
        limit = findElem(inputs, "/by");
        if (limit == -1) {
            throw new TunaException("Oops! Remember to include /by and the deadline after your task description");
        }
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        by = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
    }

    /**
     * Executes the add deadline command, adding the DeadLine Task to the tasks list.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        tasks.addDeadLine(taskDescription, by);
        return ui.taskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}
