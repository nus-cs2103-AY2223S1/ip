package duke.commands;

import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends TaskListIndexCommand {

    private static final String markedText = "Nice! I've marked this task as done:";
    private static final String unmarkedText = "Noted. I've removed this task:";

    private final boolean statusToMark;

    public MarkCommand(String invoker, TaskList taskList, boolean statusToMark) {
        super(invoker, taskList);
        this.statusToMark = statusToMark;
    }

    private String outputText() {
        return statusToMark
                ? markedText
                : unmarkedText;
    }

    @Override
    protected String execute(Task t) {
        t.setComplete(statusToMark);
        return outputText() + "\n  " + t;
    }
}
