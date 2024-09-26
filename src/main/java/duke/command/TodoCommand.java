package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Todo command is a command that creates a new ToDo task.
 *
 * @author Eugene Tan
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for toDo Command
     *
     * @param description Description of the toDo.
     */
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Creates a new ToDo with a given description.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return Add ToDo message
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addToDo(this.description);
        storage.save(tasks.getTaskListInString());
        return ui.printAdd(task, tasks.getSize());
    }
}
