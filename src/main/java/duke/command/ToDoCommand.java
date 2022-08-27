package duke.command;

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
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        String output = "";
        output += ui.printAddTask(taskList.addTask(new ToDo(description))) + '\n';
        output += ui.printSizeOfList(taskList.size());
        storage.save(taskList.getTasks());
        return output;
    }
}
