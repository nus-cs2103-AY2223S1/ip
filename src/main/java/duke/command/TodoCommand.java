package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * TodoCommand is a Command that handles todo.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for TodoCommand.
     *
     * @param description A String description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Outputs the todo task being added.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws IOException The exception thrown when accessing files is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.addTodo(description);
        storage.save(tasks);
        ui.displayAdd(task, tasks.getSize());
    }
}
