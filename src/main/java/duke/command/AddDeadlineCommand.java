package duke.command;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param ui An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.addNewTask(task);
        } catch (DukeException exception) {
            output = exception.getMessage();
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
