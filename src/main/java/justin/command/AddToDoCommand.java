package justin.command;

import justin.*;
import justin.task.Task;
import justin.task.ToDo;

/**
 * Represents a command that is called to add a ToDo
 * task in the TaskList, and save changes.
 * @author Justin Cheng.
 */
public class AddToDoCommand extends Command {
    private String[] description;
    private boolean isDone;

    /**
     * Constructor for the ToDo class.
     * @param description The description of the ToDo task.
     * @param isDone The boolean value of whether the task is done.
     */
    public AddToDoCommand(boolean isDone, String... description) {
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
     * @return The String message of the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        try {
            assert description.length <= 30 : "Too many tasks to add!";
        } catch (AssertionError e) {
            throw new DukeException(e.getMessage());
        }
        String msg = ui.getAddMessage() + ui.getSeparator();
        for (String des : description) {
            Task task = new ToDo(des, isDone);
            list.addTask(task);
            storage.save(list);
            msg += task + ui.getSeparator();
        }
        return msg + ui.getCountMessage(list);
    }
}
