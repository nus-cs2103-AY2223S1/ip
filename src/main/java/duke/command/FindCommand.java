package duke.command;

import java.util.stream.Stream;

import duke.Duke;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to find Tasks with given information.
 */
public class FindCommand extends Command {
    private String target;

    /**
     * Constructor of the class.
     * @param target The given String.
     */
    public FindCommand(String target) {
        super(Action.FIND);
        this.target = target;
    }

    /**
     * Returns the target String.
     * @return The target String.
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        TaskList tasks = duke.getTaskList();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String message = "Here are the matching tasks in your list:\n";
        TaskList temp = new TaskList();
        Stream.iterate(0, x -> x + 1).limit(tasks.size())
                .map(i -> tasks.get(i))
                .filter(task -> task.getName().contains(this.target))
                .forEach(task -> temp.add(task));
        if (temp.size() == 0) {
            message = "Currently no matching tasks in the list.";
        } else {
            message = message + temp.toString();
        }
        return messagePrinter.printMessage(message);
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminated() {
        return false;
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
        if (obj == this) {
            return true;
        }
        if (obj instanceof FindCommand) {
            FindCommand c = (FindCommand) obj;
            if (this.target == c.getTarget()) {
                return true;
            }
            if (this.target == null || c.getTarget() == null) {
                return false;
            }
            return this.target.equals(c.getTarget());
        }
        return false;
    }
}
