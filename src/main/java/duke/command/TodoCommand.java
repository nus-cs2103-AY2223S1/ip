package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.addTodo(description);
        storage.save(tasks);
        return ui.displayAdd(task, tasks.getSize());
    }
}
