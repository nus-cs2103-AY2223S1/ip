package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * TodoCommand is a Command that creates a new Todo.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for TodoCommand.
     *
     * @param description Description of the Todo.
     */
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Creates a new Todo with the given description.
     *
     * @param tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addTodo(description);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks.saveTasks());
    }
}
