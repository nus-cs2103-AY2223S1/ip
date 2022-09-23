package duke.command;

import java.time.LocalDateTime;

import duke.Duke;
import duke.task.Event;
import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to create an Event Task in Duke.
 */
public class EventCommand extends AddCommand {
    private final LocalDateTime time;

    /**
     * Constructs the class.
     * @param msg The information of the Task.
     * @param time The date and time of the Task.
     */
    public EventCommand(String msg, LocalDateTime time) {
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
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        TaskList taskList = duke.getTaskList();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String successMsg = "Got it. I've added this task:";
        Task event = new Event(msg, time);
        taskList.add(event);
        successMsg = successMsg + "\n" + event + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return messagePrinter.getPrintMessage(successMsg);
    }
}
