package duke.command;

import java.time.LocalDateTime;

import duke.Duke;
import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to create a Deadline Task in Duke.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * The date and time of the Task.
     */
    private final LocalDateTime time;

    /**
     * The constructor of the class.
     * @param msg The information of the Task.
     * @param time The date and time of the Task.
     */
    public DeadlineCommand(String msg, LocalDateTime time) {
        super(Action.DEADLINE, msg);
        this.time = time;
    }

    /**
     * Returns the date and time of the Task.
     * @return The date and time of the Task.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "deadline [Deadline Name] /by [Deadline Time(yyyy-MM-dd HH:mm)]";
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        TaskList taskList = duke.getTaskList();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String successMsg = "Got it. I've added this task:";
        Task deadline = Task.deadline(msg, time);
        taskList.add(deadline);
        successMsg = successMsg + "\n" + deadline + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return messagePrinter.printMessage(successMsg);
    }

    /**
     * Returns boolean indicating whether this object
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
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand c = (DeadlineCommand) obj;
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
