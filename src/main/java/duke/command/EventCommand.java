package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand {
    LocalDateTime time;

    protected EventCommand(String msg, LocalDateTime time) {
        super(Action.EVENT, msg);
        this.time = time;
    }

    @Override
    public String getFormat() {
        return "event [Event Name] /at [Event Time(yyyy-MM-dd HH:mm)]";
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "Got it. I've added this task:";
        Task event = Task.event(msg, time);
        taskList.add(event);
        successMsg = successMsg + "\n" + event + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";
        messagePrinter.printMessage(successMsg);
    }
}
