package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * Command to create a todo task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Creates a todo command.
     *
     * @param description The description for the todo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to create a todo task and add it to specified task list.
     * It calls the ui to print the todo task and the storage to save the new task list.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            String output = "";
            output += ui.printAddTask(taskList.addTask(new ToDo(description)));
            output += ui.printSizeOfList(taskList.getSize());
            storage.save(taskList.getTasks());
            return output;
        } catch (DukeException e) {
            return ui.printErrorMessage(e);
        }
    }
}
