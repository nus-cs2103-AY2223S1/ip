package duke.command;

import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to create a Todo Task in Duke.
 */
public class TodoCommand extends AddCommand {
    /**
     * The constructor of the Class.
     * @param msg The information of the Task.
     */
    protected TodoCommand(String msg) {
        super(Action.TODO, msg);
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "todo [Name]";
    }

    /**
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     */
    public String execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String msg = this.msg;
        String successMsg = "Got it. I've added this Task:";
        Task todo = Task.todo(msg);
        taskList.add(todo);
        successMsg = successMsg + "\n" + todo + "\n"
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
