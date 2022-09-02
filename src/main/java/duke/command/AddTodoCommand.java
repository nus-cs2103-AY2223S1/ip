package duke.command;

import duke.exception.DukeException;
import duke.task.TodoTask;
import duke.ui.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class that adds a to-do task, displays the output, and saves the file.
 */
public class AddTodoCommand extends Command {

    private final TodoTask task;

    /**
     * The standard constructor.
     */
    public AddTodoCommand(TodoTask task) {
        super(CommandType.ADD_TODO);
        this.task = task;
    }

    /**
     * Executes the command concretely.
     * Adds a to-do task, displays the output, and saves the file.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     * @return String to be displayed on the screen as a response to the user input.
     */
    @Override
    protected String executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.addNewTask(task);
        } catch (DukeException exception) {
            output = exception.getMessage();
        }

        cliUi.printOutput(output);

        super.saveFile(cliUi, taskList, storage);
        return output;
    }
}
