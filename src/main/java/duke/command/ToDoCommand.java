package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ToDoCommand is a Command that creates a new ToDo.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class ToDoCommand extends Command {
    private String description;

    /**
     * A constructor for ToDoCommand.
     *
     * @param description The description of the ToDo.
     */
    public ToDoCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * A method that creates a new ToDo and adds it to the TaskList, displays the add-message, and updates the Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addToDo(description);
        int size = tasks.getSize();
        ui.showAdd(tasks.getTask(size - 1), size);
        storage.save(tasks.saveToStorage());
    }
}
