package justin.command;

import justin.task.Deadline;
import justin.DukeException;
import justin.Storage;
import justin.task.Task;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command that is called to add a deadline
 * task in the TaskList, and save changes.
 * @author Justin Cheng.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private boolean isDone;
    private String by;
    private String time;

    /**
     * Constructor for the AddDeadlineCommand class.
     * @param description This is the description of the task.
     * @param isDone This is the boolean value of whether the task is done.
     * @param by This is the date of which the task is due.
     * @param time This is the time of which the task is due.
     */
    public AddDeadlineCommand(String description, boolean isDone, String by, String time) {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
        this.time = time;
    }

    /**
     * Second constructor for the AddDeadlineCommand class.
     * @param description This is the description of the task.
     * @param msg This is the array of strings which contain details
     *            such as its time and date in string format.
     */
    public AddDeadlineCommand(String description, String[] msg) {
        this.description = description;
        this.isDone = false;
        this.by = msg[1];
        this.time = msg[2];
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
            Task task = new Deadline(description, isDone, by, time);
            list.addTask(task);
            ui.addMessage(task);
            ui.countMessage(list);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}
