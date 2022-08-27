package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Todo;
import duke.Ui;

/**
 * Command to add a {@code Todo} to the {@code TaskList}
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructor for {@code TodoCommand}
     * @param description the description of the {@code Todo}
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * To execute the {@code TodoCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.todoTask(tasks, todo);
        storage.update(tasks.getTasks());
    }
}
