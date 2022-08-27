package duke.command;

import java.util.Arrays;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     */
    public AddEventCommand(String[] inputs) {
        super(CommandType.EVENT);
        limit = findElem(inputs, "/at");
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        at = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
    }

    /**
     * Executes the add event command, adding the Event Task to the tasks list.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws DukeException exception thrown when /at is missing.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (limit == -1) {
            ui.printEventErrorMessage();
            return;
        }
        tasks.addEvent(taskDescription, at);
        ui.printTaskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}

