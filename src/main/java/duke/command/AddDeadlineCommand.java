package duke.command;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.CliUi;

/**
 * A command class that adds a deadline task, displays the output, and saves the file.
 */
public class AddDeadlineCommand extends Command {

    private final DeadlineTask task;

    /**
     * The standard constructor.
     */
    public AddDeadlineCommand(DeadlineTask task) {
        super(CommandType.ADD_DEADLINE);
        this.task = task;
    }

    /**
     * Executes the command concretely.
     *
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
