package duke.commands;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to mark the completion status of a task.
 */
public class MarkCommand extends TaskListIndexCommand {

    /**
     * The text that is displayed when a task is marked as done.
     */
    private static final String MARKED_TEXT = "Nice! I've marked this task as done:";
    /**
     * The text that is displayed when a task is unmarked.
     */
    private static final String UNMARKED_TEXT = "Noted. I've unchecked this task:";

    /**
     * The completion status that this command will mark a task as.
     */
    private final boolean statusToMark;

    /**
     * Constructor for a MarkCommand.
     *
     * @param invoker      The string used to invoke the execution of this command.
     * @param taskList     The TaskList that this command adds a task to.
     * @param statusToMark The completion status that this command will mark a task as.
     */
    public MarkCommand(String invoker, TaskList taskList, boolean statusToMark) {
        super(invoker, taskList);
        this.statusToMark = statusToMark;
    }

    private String outputText() {
        return statusToMark
                ? MARKED_TEXT
                : UNMARKED_TEXT;
    }

    /**
     * Sets the completion status of a task.
     *
     * @param t Task to set completion status of.
     * @return String output of the marking status.
     */
    @Override
    protected String execute(Task t) {
        t.setComplete(statusToMark);
        return outputText() + "\n  " + t;
    }
}
