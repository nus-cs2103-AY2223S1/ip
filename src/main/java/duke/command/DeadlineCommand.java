package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    private LocalDateTime time;

    protected DeadlineCommand(String msg, LocalDateTime time) {
        super(Action.DEADLINE, msg);
        this.time = time;
    }

    @Override
    public String getFormat() {
        return "deadline [Deadline Name] /by [Deadline Time(yyyy-MM-dd HH:mm)]";
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "Got it. I've added this task:";
        Task deadline = Task.deadline(msg, time);
        taskList.add(deadline);
        successMsg = successMsg + "\n" + deadline + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";
        messagePrinter.printMessage(successMsg);
    }
}
