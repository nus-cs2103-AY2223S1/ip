package justin.command;

import justin.DukeException;
import justin.Storage;
import justin.task.Task;
import justin.TaskList;
import justin.task.ToDo;
import justin.Ui;

/**
 * Represents a command that is called to add a ToDo
 * task in the TaskList, and save changes.
 * @author Justin Cheng.
 */
public class AddToDoCommand extends Command {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the ToDo class.
     * @param description The description of the ToDo task.
     * @param isDone The boolean value of whether the task is done.
     */
    public AddToDoCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Executes the task, which consists of
     * adding task to TaskList, sending messages through Ui,
     * and saving tasks to Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task task = new ToDo(description, isDone);
            list.addTask(task);
            ui.addMessage(task);
            ui.countMessage(list);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}
