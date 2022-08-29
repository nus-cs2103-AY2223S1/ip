package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark a task as not done. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task task = taskList.get(this.index);
            task.markNotDone();
            return String.format("OK, I've marked this task as not done yet:\n\t\t %s", task.toString());
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task index";
        }
    }
}
