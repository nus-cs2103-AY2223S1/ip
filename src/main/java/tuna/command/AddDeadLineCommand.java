package tuna.command;

import java.util.Arrays;

import tuna.Storage;
import tuna.TaskList;
import tuna.TunaException;
import tuna.Ui;

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
     */
    public AddDeadLineCommand(String[] inputs) {
        super(CommandType.DEADLINE);
        limit = findElem(inputs, "/by");
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        by = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
    }

    /**
     * Executes the add deadline command, adding the DeadLine Task to the tasks list.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws TunaException exception thrown when /by is missing.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        if (limit == -1) {
            return ui.deadLineErrorMessage();
        }
        tasks.addDeadLine(taskDescription, by);
        return ui.taskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}
