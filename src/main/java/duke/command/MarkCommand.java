package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand implements Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark a task as done. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task task = taskList.get(this.index);
            task.markAsDone();
            return String.format("Nice! I've marked this task as done:\n\t\t %s", task.toString());
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task index";
        }
    }
}
