package justin.command;

import justin.*;
import justin.task.Event;
import justin.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a command that is called to add an event
 * task in the TaskList, and save changes.
 * @author Justin Cheng.
 */
public class AddEventCommand extends Command {
    private String description;
    private boolean isDone;
    private String at;
    private String time;
    private Event task;
    private ArrayList<Task> overlappedTasks;

    /**
     * Constructor for the AddEventCommand class
     * @param description The description of the event.
     * @param isDone The boolean value of whether the task is done.
     * @param at The date at which the event is happening.
     * @param time The time at which the event is happening.
     */
    public AddEventCommand(String description, boolean isDone, String at, String time) {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
        this.time = time;
    }

    /**
     * Second constructor for the AddEventCommand class
     * @param description The description of the event.
     * @param msg The array of strings which contains details of the task,
     *            such as its date and time in string format.
     */
    public AddEventCommand(String description, String... msg) {
        this.description = description;
        this.isDone = false;
        this.at = msg[1];
        this.time = msg[2];
    }

    /**
     * Executes the task, which consists of
     * adding task to TaskList, sending messages through Ui,
     * and saving tasks to Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to store changes.
     * @return The String message of the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        task = new Event(description, isDone, at, time);
        if (task.isOverlap(list)) {
            setOverlappedTasks(task, list);
            return ui.getOverlapMessage(overlappedTasks);
        } else {
            list.addTask(task);
            storage.save(list);
            return ui.getAddMessage() + ui.getSeparator() + task + ui.getSeparator() + ui.getCountMessage(list);
        }
    }

    /**
     * Adds all overlapped tasks with an Event object
     * to an ArrayList.
     * @param event The Event object to be compared against.
     * @param tasks The TaskList to refer to for
     *              overlapped tasks.
     */
    public void setOverlappedTasks(Event event, TaskList tasks) {
        overlappedTasks = new ArrayList<>();
        for (Task t: tasks.getTasks()) {
            if (!(t instanceof Event)) {
                continue;
            }
            Event curr = (Event) t;
            if (event.isEqualDateAndTime(curr)) {
                overlappedTasks.add(curr);
            }
        }
    }

}
