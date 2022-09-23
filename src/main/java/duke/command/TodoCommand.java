package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to create a Todo Task in Duke.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs the class.
     * @param msg The information of the Task.
     */
    public TodoCommand(String msg) {
        super(Action.TODO, msg);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    public String execute(Duke duke) {
        TaskList taskList = duke.getTaskList();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String msg = this.msg;
        String successMsg = "Got it. I've added this Task:";
        Task todo = Task.todo(msg);
        taskList.add(todo);
        successMsg = successMsg + "\n" + todo + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return messagePrinter.getPrintMessage(successMsg);
    }
}
