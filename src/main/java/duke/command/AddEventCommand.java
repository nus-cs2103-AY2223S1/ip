package duke.command;

import duke.exception.DukeException;
import duke.task.EventTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.CliUi;

/**
 * A command class that adds an event task, displays the output, and saves the file.
 */
public class AddEventCommand extends Command {

    private final EventTask task;

    /**
     * The standard constructor.
     */
    public AddEventCommand(EventTask task) {
        super(CommandType.ADD_EVENT);
        this.task = task;
    }

    /**
     * Executes the command concretely.
     * Adds an event task, displays the output, and saves the file.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.addNewTask(task);
        } catch (DukeException exception) {
            output = exception.getMessage();
        }

        cliUi.printOutput(output);

        super.saveFile(cliUi, taskList, storage);
    }
}
