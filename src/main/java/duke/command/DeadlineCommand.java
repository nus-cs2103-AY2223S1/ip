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

    public LocalDateTime getTime() {
        return this.time;
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
