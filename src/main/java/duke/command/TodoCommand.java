package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class TodoCommand extends AddCommand {
    protected TodoCommand(String msg) {
        super(Action.TODO, msg);
    }

    @Override
    public String getFormat() {
        return "todo [Name]";
    }

    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String msg = this.msg;
        String successMsg = "Got it. I've added this Task:";
        Task todo = Task.todo(msg);
        taskList.add(todo);
        successMsg = successMsg + "\n" + todo + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        messagePrinter.printMessage(successMsg);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand c = (DeadlineCommand) obj;
            if (this.getMsg() == c.getMsg()) {
                return true;
            }
            if (this.getMsg() == null || c.getMsg() == null) {
                return false;
            }
            return this.getMsg().equals(c.getMsg());
        }
        return false;
    }
}
