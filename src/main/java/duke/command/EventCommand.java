package duke.command;

import java.time.LocalDateTime;

import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to create an Event Task in Duke.
 */
public class EventCommand extends AddCommand {
    /**
     * The date and time of the Task.
     */
    private final LocalDateTime time;

    /**
     * The constructor of the class.
     * @param msg The information of the Task.
     * @param time The date and time of the Task.
     */
    protected EventCommand(String msg, LocalDateTime time) {
        super(Action.EVENT, msg);
        this.time = time;
    }

    /**
     * Returns the date and time of the Task.
     * @return The date and time of the Task.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "event [Event Name] /at [Event Time(yyyy-MM-dd HH:mm)]";
    }

    /**
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     */
    @Override
    public String execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "Got it. I've added this task:";
        Task event = Task.event(msg, time);
        taskList.add(event);
        successMsg = successMsg + "\n" + event + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return messagePrinter.printMessage(successMsg);
    }

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EventCommand) {
            EventCommand c = (EventCommand) obj;
            if (this.getMsg() == c.getMsg() && this.time == c.getTime()) {
                return true;
            }
            if (this.getMsg() == null || c.getMsg() == null) {
                return false;
            }
            if (this.getTime() == null || c.getTime() == null) {
                return false;
            }
            return this.getTime().equals(c.getTime()) && this.getMsg().equals(c.getMsg());
        }
        return false;
    }
}
